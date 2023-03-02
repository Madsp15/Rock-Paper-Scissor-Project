package rps.gui.model;

import javafx.scene.image.Image;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel {

    private GameManager gameManager;
    private Image humanImage;
    private Image aiImage;

    public GameViewModel(IPlayer human, IPlayer bot){
        this.gameManager = new GameManager(human, bot);
    }

    public void playRound(Move human_move){
        gameManager.playRound(human_move);
    }
    public ArrayList<Result> getHistoricResults(){
        return (ArrayList<Result>) gameManager.getGameState().getHistoricResults();
    }

    public void setImages(Image human, Image ai){
        this.humanImage = human;
        this.aiImage = ai;
    }
    public Image getHumanImage(){
        return humanImage;
    }
    public Image getAiImage(){
        return aiImage;
    }
}
