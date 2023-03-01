package rps.gui.model;

import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel {

    private GameManager gameManager;

    public GameViewModel(IPlayer human, IPlayer bot){
        this.gameManager = new GameManager(human, bot);
    }

    public void playRound(Move human_move){
        gameManager.playRound(human_move);
    }
    public ArrayList<Result> getHistoricResults(){
        return (ArrayList<Result>) gameManager.getGameState().getHistoricResults();
    }
}
