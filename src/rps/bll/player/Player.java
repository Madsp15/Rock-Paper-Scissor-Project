package rps.bll.player;

//Project imports
import rps.bll.game.*;

//Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;
    private final MarkovChain markovChain = new MarkovChain();
    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     *
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        GameState gameState = (GameState) state;
        return markovPrediction(gameState);

    }

    public Move simpleNextSequence(ArrayList<Result> results){
        //If AI won last round then it picks the previous move in the sequence
        if(results.get(results.size()-1).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)){
            if(results.get(results.size()-1).getWinnerMove().equals(Move.Rock))
                return Move.Scissor;
            if(results.get(results.size()-1).getWinnerMove().equals(Move.Paper))
                return Move.Rock;
            if(results.get(results.size()-1).getWinnerMove().equals(Move.Scissor))
                return Move.Paper;

            //If last round was a tie, AI does the same move
        } else if (results.get(results.size()-1).getType().equals(ResultType.Tie)) {
            return results.get(results.size()-1).getLoserMove();
        }

        //Otherwise it won and moves back two moves in the sequence
        if(results.get(results.size()-1).getWinnerMove().equals(Move.Rock))
            return Move.Paper;
        if(results.get(results.size()-1).getWinnerMove().equals(Move.Paper))
            return Move.Scissor;
        if(results.get(results.size()-1).getWinnerMove().equals(Move.Scissor))
            return Move.Rock;

        return null;
    }

    public Move markovPrediction(GameState gameState) {
        List<Result> results = gameState.getHistoricResults();
        if (results.size() <= 2) {
            // No results yet, make a random move
            return Move.values()[(int) (Math.random() * 3)];
        } else {
            Result lastResult = results.get(results.size() - 2);
            Result nextResult = results.get(results.size() - 1);
            //move ai made
            Move previousMove = null;
            //move human made next round
            Move nextMove = null;
            //checks if human player won the last two games
            if(lastResult.getWinnerPlayer().getPlayerType().equals(PlayerType.Human) && nextResult.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)){
                previousMove = lastResult.getLoserMove();
                nextMove = nextResult.getWinnerMove();
            }
            //checks if ai won the last two games
            if(lastResult.getWinnerPlayer().getPlayerType().equals(PlayerType.AI) && nextResult.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)){
                previousMove = lastResult.getWinnerMove();
                nextMove = nextResult.getLoserMove();
            }
            //checks if human won the first game and ai won the second game
            if(lastResult.getWinnerPlayer().getPlayerType().equals(PlayerType.Human) && nextResult.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)){
                previousMove = lastResult.getLoserMove();
                nextMove = nextResult.getLoserMove();
            }
            //checks if ai won the first game and human won the second game
            if(lastResult.getWinnerPlayer().getPlayerType().equals(PlayerType.AI) && nextResult.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)){
                previousMove = lastResult.getWinnerMove();
                nextMove = nextResult.getWinnerMove();
            }
            markovChain.updateTransitionMatrix(previousMove, nextMove);
            return markovChain.predictNextMove(previousMove);
        }
    }

    public Move previousThreeGuess(ArrayList<Result> results){
        int rock = 0;
        int paper = 0;
        int scissor = 0;

        if(results.size()<5) {
            Random rd = new Random();
            int rdNum = rd.nextInt(((5 - 1) + 1));
            if (rdNum == 0)
                return Move.Rock;
            if (rdNum >= 2)
                return Move.Paper;
            if (rdNum == 1)
                return Move.Scissor;
        }
        for (int i = results.size() - 3; i < results.size(); i++) {
            if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                if (results.get(i).getWinnerMove().equals(Move.Rock))
                    rock++;
                if (results.get(i).getWinnerMove().equals(Move.Paper))
                    paper++;
                if (results.get(i).getWinnerMove().equals(Move.Scissor))
                    scissor++;
            } else if (results.get(i).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                if (results.get(i).getLoserMove().equals(Move.Rock))
                    rock++;
                if (results.get(i).getLoserMove().equals(Move.Paper))
                    paper++;
                if (results.get(i).getLoserMove().equals(Move.Scissor))
                    scissor++;
            }
        }
        if (rock > paper && rock > scissor) {
            if (paper >= scissor)
                return Move.Rock;
            return Move.Scissor;
        }
        if (paper > rock && paper > scissor) {
            if (scissor >= rock)
                return Move.Paper;
            return Move.Rock;
        }
        if (scissor > paper && scissor > rock){
            if (paper >= rock)
                return Move.Paper;
            return Move.Scissor;
        }
        if(rock == paper && paper == scissor) {
            return Move.Paper;
        }
        return null;
    }
}
