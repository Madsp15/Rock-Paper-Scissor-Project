package rps.bll.game;

// Project imports
import rps.bll.player.IPlayer;

/**
 * Defines a Result in the game
 *
 * @author smsj
 */
public class Result {
    private ResultType type;
    private Move winnerMove;
    private IPlayer winnerPlayer;
    private Move loserMove;
    private IPlayer loserPlayer;
    private int roundNumber;
    private int aiHealthBar;
    private int playerHealthBar;


    /**
     * Initializes a new Result with a winner, loser, their moves and a type
     * @param winnerPlayer
     * @param winnerMove
     * @param loserPlayer
     * @param loserMove
     * @param type
     * @param roundNumber
     */
    public Result(IPlayer winnerPlayer, Move winnerMove, IPlayer loserPlayer, Move loserMove, ResultType type, int roundNumber, int aiHealthBar, int playerHealthBar) {
        this.winnerPlayer = winnerPlayer;
        this.winnerMove = winnerMove;
        this.loserPlayer = loserPlayer;
        this.loserMove = loserMove;
        this.type = type;
        this.roundNumber = roundNumber;
        this.aiHealthBar = aiHealthBar;
        this.playerHealthBar = playerHealthBar;
    }

    public Move getWinnerMove() {
        return winnerMove;
    }

    public IPlayer getWinnerPlayer() {
        return winnerPlayer;
    }

    public Move getLoserMove() {
        return loserMove;
    }

    public int getAiHealthBar()
    {
        return aiHealthBar;
    }

    public int getPlayerHealthBar()
    {
        return playerHealthBar;
    }


    public IPlayer getLoserPlayer() {
        return loserPlayer;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public ResultType getType() {
        return type;
    }
}


