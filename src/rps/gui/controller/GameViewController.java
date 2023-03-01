package rps.gui.controller;

// Java imports
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.PlayerType;
import rps.gui.model.GameViewModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelHumanName, labelAIName, labelScore, labelAIChat;
    @FXML
    private ImageView imageActiveRPCPlayer, imageActiveRPCAI, imageAIPlayer, imageHumanPlayer, imageVersus, imageChatBox;
    @FXML
    private MFXTextField textFieldAI;
    @FXML
    private ImageView imageRockButton, imagePaperButton, imageScissorButton;
    private final Image chatBox = new Image("Pictures/speech_bubble.png");
    private final Image versus = new Image("Pictures/versus.png");
    private final Image rock = new Image("Pictures/rock.png");
    private final Image paper = new Image("Pictures/paper.png");
    private final Image scissors = new Image("Pictures/scissors.png");
    private final Image questionR = new Image("Pictures/qRed.png");
    private final Image questionO = new Image("Pictures/qOrange.png");
    private final Image questionY = new Image("Pictures/qYellow.png");
    private final Image questionG = new Image("Pictures/qGreen.png");
    private final Image questionB = new Image("Pictures/qBlue.png");
    private final Image questionP = new Image("Pictures/qPurple.png");
    private List<Image> questionMarks = new ArrayList<>();
    private GameViewModel gameViewModel;
    private Timeline timeline;
    private int currentIndex = 0;
    private int cycleCount = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageChatBox.setImage(chatBox);
        imageVersus.setImage(versus);
        imageRockButton.setImage(rock);
        imagePaperButton.setImage(paper);
        imageScissorButton.setImage(scissors);
        imageActiveRPCPlayer.setImage(questionR);
        imageActiveRPCAI.setImage(questionB);
        questionMarks.clear();
        questionMarks.add(questionR);
        questionMarks.add(questionO);
        questionMarks.add(questionY);
        questionMarks.add(questionG);
        questionMarks.add(questionB);
        questionMarks.add(questionP);


    }

    public void setGameViewModel(GameViewModel model){
        this.gameViewModel = model;
    }

    public void clickRock(MouseEvent mouseEvent) {
        timeline = new Timeline();
        imageActiveRPCPlayer.setImage(rock);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            currentIndex = (currentIndex + 1) % questionMarks.size();
            imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
            if(currentIndex == 0)
                cycleCount++;
            if(cycleCount == 3){
                timeline.stop();
                imageActiveRPCAI.setImage(rock);
                playRound(Move.Rock);
                cycleCount = 0;
            }

        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(20);

        timeline.play();

    }

    public void clickPaper(MouseEvent mouseEvent) {
        timeline = new Timeline();
        imageActiveRPCPlayer.setImage(paper);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            currentIndex = (currentIndex + 1) % questionMarks.size();
            imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
            if(currentIndex == 0)
                cycleCount++;
            if(cycleCount == 3){
                timeline.stop();
                imageActiveRPCAI.setImage(paper);
                playRound(Move.Paper);
                cycleCount = 0;
            }

        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(20);

        timeline.play();

    }

    public void clickScissor(MouseEvent mouseEvent) {
        timeline = new Timeline();
        imageActiveRPCPlayer.setImage(scissors);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            currentIndex = (currentIndex + 1) % questionMarks.size();
            imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
            if(currentIndex == 0)
                cycleCount++;
            if(cycleCount == 3){
                timeline.stop();
                imageActiveRPCAI.setImage(scissors);
                playRound(Move.Scissor);
                cycleCount = 0;
            }

        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(20);

        timeline.play();

    }

    public void playRound(Move move) {
        ArrayList<Result> results = new ArrayList<>();

        gameViewModel.playRound(move);

        results = gameViewModel.getHistoricResults();
        if (results.get(results.size() - 1).getType().equals(ResultType.Tie)) {
            if (move.equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (move.equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (move.equals(Move.Scissor))
                imageActiveRPCAI.setImage(paper);
        }
        if (results.get(results.size() - 1).getType().equals(ResultType.Win) && results.get(results.size() - 1).getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            if (results.get(results.size() - 1).getWinnerMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (results.get(results.size() - 1).getWinnerMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (results.get(results.size() - 1).getWinnerMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(paper);
        }
        if (results.get(results.size() - 1).getType().equals(ResultType.Win) && results.get(results.size() - 1).getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            if (results.get(results.size() - 1).getLoserMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (results.get(results.size() - 1).getLoserMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (results.get(results.size() - 1).getLoserMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(paper);
        }

        System.out.println(results.get(results.size()-1).getRoundNumber() + ": " + results.get(results.size()-1).getWinnerPlayer() + " "
                + results.get(results.size()-1).getType() + " with " + results.get(results.size()-1).getWinnerMove());

    }
}
