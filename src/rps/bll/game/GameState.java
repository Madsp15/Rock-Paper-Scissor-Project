package rps.bll.game;

//Java imports
import java.util.ArrayList;

/**
 * Keeps track of game state including historic results
 *
 * @author smsj
 */
public class GameState implements IGameState {

    private ArrayList<Result> historicResults;
    private int roundNumber;
    private int aiHealthbar = 5;
    private int playerHealthbar = 5;

    /**
     *
     * @param historicResults
     * @param roundNumber
     */
    public GameState(ArrayList<Result> historicResults, int roundNumber) {
        this.historicResults = new ArrayList<>();
        this.roundNumber = roundNumber;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public int getAiHealth() {
        return aiHealthbar;
    }

    @Override
    public void setAiHealth(int aiHealth) {
        this.aiHealthbar = aiHealth;
    }

    @Override
    public int getPlayerHealth() {
        return playerHealthbar;
    }

    @Override
    public void setPlayerHealth(int playerHealth) {
        this.playerHealthbar = playerHealth;
    }


    @Override
    public ArrayList<Result> getHistoricResults() {
        return historicResults;
    }
}
