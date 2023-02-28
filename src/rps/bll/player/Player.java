package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

//Java imports
import java.util.ArrayList;
import java.util.Random;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

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
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        int rock = 0;
        int paper = 0;
        int scissor = 0;

        //Implement better AI here...
        if(results.size()<5) {
            Random rd = new Random();
            int rdNum = rd.nextInt(((5 - 1) + 1));
            System.out.println(rdNum);
            if (rdNum == 0)
                return Move.Rock;
            if (rdNum >= 2)
                return Move.Paper;
            if (rdNum == 1)
                return Move.Scissor;
        }
        for (int i = results.size() - 3; i < results.size(); i++) {
            System.out.println(results.get(i).getWinnerMove());
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
            System.out.println(0);
            if (paper >= scissor)
                return Move.Rock;
            return Move.Scissor;
        }
        if (paper > rock && paper > scissor) {
            System.out.println(1);
            if (scissor >= rock)
                return Move.Paper;
            return Move.Rock;
        }
        if (scissor > paper && scissor > rock){
            System.out.println(2);
            if (paper >= rock)
                return Move.Paper;
            return Move.Scissor;
        }
        if(rock == paper && paper == scissor) {
            System.out.println(3);
            return Move.Paper;
        }


        return null;
    }
}
