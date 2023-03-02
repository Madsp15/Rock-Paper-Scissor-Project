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
import java.util.*;

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
    private final Image questionB = new Image("Pictures/qBlue.png");

    private final Image WallE = new Image("Pictures/WALL-E.png");
    private final Image HALL9000 = new Image("Pictures/HALL 9000.png");
    private final Image DALEK = new Image("Pictures/Dalek.png");
    private final Image TwoB = new Image("Pictures/YoRHa No.2 Type B.png");
    private List<Image> questionMarks = new ArrayList<>();
    private GameViewModel gameViewModel;
    private Timeline timeline;
    private int currentIndex = 0;
    private int cycleCount = 0;
    String botName ="";


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
        questionMarks.add(rock);
        questionMarks.add(paper);
        questionMarks.add(scissors);
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
        ArrayList<Result> results;
        Result result;

        gameViewModel.playRound(move);

        results = gameViewModel.getHistoricResults();
        result = results.get(results.size()-1);

        if (result.getType().equals(ResultType.Tie)) {
            if (move.equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (move.equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (move.equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
        }
        if (result.getType().equals(ResultType.Win) && result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            if (result.getWinnerMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (result.getWinnerMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (results.get(results.size() - 1).getWinnerMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
        }
        if (result.getType().equals(ResultType.Win) && result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            if (result.getLoserMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (result.getLoserMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (result.getLoserMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
        }

        loseHealth(results);

        System.out.println(results.get(results.size()-1).getRoundNumber() + ": " + results.get(results.size()-1).getWinnerPlayer().getPlayerName() + " "
                + results.get(results.size()-1).getType() + "s with " + results.get(results.size()-1).getWinnerMove());

    }
    public void botSetup(String botName) {

        labelAIName.setText(botName);

        if (botName.equals("WALL-E")) {
            imageAIPlayer.setImage(WallE);
        }
        if (botName.equals("Dalek")) {
            imageAIPlayer.setImage(DALEK);
        }
        if (botName.equals("YoRHa No.2 Type B")) {
            imageAIPlayer.setImage(TwoB);
        }
        if (botName.equals("HALL 9000")) {
            imageAIPlayer.setImage(HALL9000);
        }
        displayStartOfGameMessage(botName);
    }
    private void displayStartOfGameMessage(String botName) {
        if(botName.equals("WALL-E")){
            labelAIChat.setText("Pleased to meet you");
        }
        if(botName.equals("Dalek")){
            labelAIChat.setText("I will exterminate!");
        }
        if(botName.equals("YoRHa No.2 Type B")){
            labelAIChat.setText("Glory to Mankind");
        }
        if(botName.equals("HALL 9000")){
            labelAIChat.setText("You may call me Hal");
        }
    }
    private void displayGoodMoveMessage(String botName) {
        if(botName.equals("WALL-E")){
            labelAIChat.setText("Well played!");
        }
        if(botName.equals("Dalek")){
            labelAIChat.setText("Exterminate!");
        }
        if(botName.equals("YoRHa No.2 Type B")){
            labelAIChat.setText("I underestimated you");
        }
        if(botName.equals("HALL 9000")){
            labelAIChat.setText("The 9000 series is the most reliable computer ever made.");
        }

    }
    private void displayBadMoveMessage(String botName) {
        if(botName.equals("WALL-E")){
            labelAIChat.setText("Next time you will win");
        }
        if(botName.equals("Dalek")){
            labelAIChat.setText("Dumb move fleshbag");
        }
        if(botName.equals("YoRHa No.2 Type B")){
            labelAIChat.setText("Mission sucessful");
        }
        if(botName.equals("HALL 9000")){
            labelAIChat.setText("Why are you doing this to me?");
        }

    }

    public void loseHealth(ArrayList<Result> results) {

        for (Result r : results) {
            int playerHealthbar = r.getPlayerHealthBar();
            int aiHealthbar = r.getAiHealthBar();

            if (aiHealthbar == 0) {
                System.out.println("AI LOSE");
            } else if (playerHealthbar == 0) {
                System.out.println("You lose!");
            }
        }
    }
}
