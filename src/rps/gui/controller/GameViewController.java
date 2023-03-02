package rps.gui.controller;

// Java imports
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.PlayerType;
import rps.gui.model.GameViewModel;

import java.io.IOException;
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
    private Label labelHumanName, labelAIName, labelScore, labelAIChat, labelWinnerPlayer, labelWinnerMove;
    @FXML
    private ImageView imageActiveRPCPlayer, imageActiveRPCAI, imageAIPlayer, imageHumanPlayer, imageVersus, imageChatBox;
    @FXML
    private MFXTextField textFieldAI;
    @FXML
    private ImageView imageRockButton, imagePaperButton, imageScissorButton,
            playerHealth1, playerHealth2, playerHealth3, playerHealth4, playerHealth5,
            aiHealth1, aiHealth2, aiHealth3, aiHealth4, aiHealth5;
    private final Image chatBox = new Image("Pictures/speech_bubble.png");
    private final Image versus = new Image("Pictures/versus.png");
    private final Image rock = new Image("Pictures/rock.png");
    private final Image paper = new Image("Pictures/paper.png");
    private final Image scissors = new Image("Pictures/scissors.png");
    private final Image questionR = new Image("Pictures/qRed.png");
    private final Image questionB = new Image("Pictures/qBlue.png");
    private final List<Image> questionMarks = new ArrayList<>();
    //private final Image health1 = new Image("Pictures/heartred.png");
    //private final Image health2 = new Image("Pictures/heartgreen.png");
    //private final Image health3 = new Image("Pictures/heartyellow.png");
    //private final Image health4 = new Image("Pictures/heartpurple.png");
    //private final Image health5 = new Image("Pictures/heartblue.png");
    //private final Image health0 = new Image("Pictures/heartgrey.png");
    private GameViewModel gameViewModel;
    private Timeline timeline;
    private int currentIndex = 0;
    private int cycleCount = 0;
    private boolean isCycling = false;
    public static boolean isHealthOn = false;


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


        //if(isHealthOn == true)
        //{
        //    setHeartImages();
        //}
    }

    public boolean isHealthModeOn(int value) {
        if (value == 1)
            return isHealthOn = true;
        else
            return isHealthOn = false;
    }


    public void setGameViewModel(GameViewModel model) {
        this.gameViewModel = model;
    }

    public void clickRock(MouseEvent mouseEvent) {
        if (!isCycling) {
            timeline = new Timeline();
            imageActiveRPCPlayer.setImage(rock);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
                currentIndex = (currentIndex + 1) % questionMarks.size();
                imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
                if (currentIndex == 0)
                    cycleCount++;
                if (cycleCount == 3) {
                    timeline.stop();
                    imageActiveRPCAI.setImage(rock);
                    playRound(Move.Rock);
                    cycleCount = 0;
                    isCycling = false;
                }

            });

            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(20);

            timeline.play();
        }

    }

    public void clickPaper(MouseEvent mouseEvent) {
        if (!isCycling) {
            timeline = new Timeline();
            imageActiveRPCPlayer.setImage(paper);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
                currentIndex = (currentIndex + 1) % questionMarks.size();
                imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
                if (currentIndex == 0)
                    cycleCount++;
                if (cycleCount == 3) {
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

    }

    public void clickScissor(MouseEvent mouseEvent) {
        timeline = new Timeline();
        imageActiveRPCPlayer.setImage(scissors);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            currentIndex = (currentIndex + 1) % questionMarks.size();
            imageActiveRPCAI.setImage(questionMarks.get(currentIndex));
            if (currentIndex == 0)
                cycleCount++;
            if (cycleCount == 3) {
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
        result = results.get(results.size() - 1);
        int humanScore = 0;
        int aiScore = 0;
        //loseHealth(results);


        if (result.getType().equals(ResultType.Tie)) {
            if (move.equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (move.equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (move.equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
            displayTieMoveMessage(result.getLoserPlayer().getPlayerName());
        }
        if (result.getType().equals(ResultType.Win) && result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
            if (result.getWinnerMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (result.getWinnerMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (results.get(results.size() - 1).getWinnerMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
            displayBadMoveMessage(result.getWinnerPlayer().getPlayerName());
        }
        if (result.getType().equals(ResultType.Win) && result.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
            if (result.getLoserMove().equals(Move.Rock))
                imageActiveRPCAI.setImage(rock);
            if (result.getLoserMove().equals(Move.Paper))
                imageActiveRPCAI.setImage(paper);
            if (result.getLoserMove().equals(Move.Scissor))
                imageActiveRPCAI.setImage(scissors);
            displayGoodMoveMessage(result.getLoserPlayer().getPlayerName());
        }

        if (isHealthOn == false) {
            for (Result res : results) {
                if (res.getType().equals(ResultType.Win) && res.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                    humanScore++;
                }
                if (res.getType().equals(ResultType.Win) && res.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                    aiScore++;
                }
            }
        }

        if (result.getType().equals(ResultType.Tie)) {
            labelWinnerMove.setText(result.getWinnerMove() + " ties with " + result.getLoserMove());
            labelWinnerPlayer.setText("Nobody wins this round");
        }
        if (result.getType().equals(ResultType.Win)) {
            labelWinnerMove.setText(result.getWinnerMove() + " beats " + result.getLoserMove());
            labelWinnerPlayer.setText(result.getWinnerPlayer().getPlayerName() + " wins round " + result.getRoundNumber());
        }

        labelScore.setText(humanScore + "  -  " + aiScore);

        System.out.println(results.get(results.size() - 1).getRoundNumber() + ": " + results.get(results.size() - 1).getWinnerPlayer().getPlayerName() + " "
                + results.get(results.size() - 1).getType() + "s with " + results.get(results.size() - 1).getWinnerMove());
    }

    public void botSetup(String humanName, String botName) {
        labelHumanName.setText(humanName);
        labelAIName.setText(botName);
        displayStartOfGameMessage(botName);
    }

    private void displayStartOfGameMessage(String botName) {
        if (botName.equals("WALL-E")) {
            labelAIChat.setText("Pleased to meet you");
        }
        if (botName.equals("Dalek")) {
            labelAIChat.setText("I will exterminate!");
        }
        if (botName.equals("YoRHa No.2 Type B")) {
            labelAIChat.setText("Glory to Mankind");
        }
        if (botName.equals("HALL 9000")) {
            labelAIChat.setText("You may call me Hal");
        }
    }

    private void displayGoodMoveMessage(String botName) {
        if (botName.equals("WALL-E")) {
            labelAIChat.setText("Well played!");
        }
        if (botName.equals("Dalek")) {
            labelAIChat.setText("Exterminate!");
        }
        if (botName.equals("YoRHa No.2 Type B")) {
            labelAIChat.setText("I underestimated you");
        }
        if (botName.equals("HALL 9000")) {
            labelAIChat.setText("The 9000 series is the most reliable computer ever made.");
        }

    }

    private void displayBadMoveMessage(String botName) {
        if (botName.equals("WALL-E")) {
            labelAIChat.setText("Next time you will win");
        }
        if (botName.equals("Dalek")) {
            labelAIChat.setText("Dumb move fleshbag");
        }
        if (botName.equals("YoRHa No.2 Type B")) {
            labelAIChat.setText("Mission sucessful");
        }
        if (botName.equals("HALL 9000")) {
            labelAIChat.setText("Why are you doing this to me?");
        }

    }

    private void displayTieMoveMessage(String botName) {
        if (botName.equals("WALL-E")) {
            labelAIChat.setText("We're the same!");
        }
        if (botName.equals("Dalek")) {
            labelAIChat.setText("Did I exterminate?");
        }
        if (botName.equals("YoRHa No.2 Type B")) {
            labelAIChat.setText("You're no match for me");
        }
        if (botName.equals("HALL 9000")) {
            labelAIChat.setText("ERROR MESSAGE 404");
        }

    }

    public void setAIHumanImage() {
        Image humanImage = gameViewModel.getHumanImage();
        Image aiImage = gameViewModel.getAiImage();
        imageHumanPlayer.setImage(humanImage);
        imageAIPlayer.setImage(aiImage);
    }

    //public void loseHealth(ArrayList<Result> results) {
    //    int humanScore = 0;
    //    int aiScore = 0;
    //    for (Result r : results) {
    //        if(isHealthOn == true) {
    //            if (r.getPlayerHealthBar() == 4) {
    //                playerHealth1.setImage(health0);
//
    //            } else if (r.getPlayerHealthBar() == 3) {
    //                playerHealth2.setImage(health0);
    //            } else if (r.getPlayerHealthBar() == 2) {
    //                playerHealth3.setImage(health0);
    //            } else if (r.getPlayerHealthBar() == 1) {
    //                playerHealth4.setImage(health0);
    //            }
    //            else if(r.getPlayerHealthBar() == 0)
    //            {
    //                playerHealth5.setImage(health0);
    //                aiScore++;
    //                setHeartImages();
    //                System.out.println("Player lose");
    //            }
//
    //            if (r.getAiHealthBar() == 4) {
    //                aiHealth5.setImage(health0);
    //            } else if (r.getAiHealthBar() == 3) {
    //                aiHealth4.setImage(health0);
    //            } else if (r.getAiHealthBar() == 2) {
    //                aiHealth3.setImage(health0);
    //            } else if (r.getAiHealthBar() == 1) {
    //                aiHealth2.setImage(health0);
    //            }
    //            else if(r.getAiHealthBar() == 0)
    //            {
    //                aiHealth1.setImage(health0);
    //                humanScore++;
    //                setHeartImages();
    //            }
    //            }
    //        }
    //    labelScore.setText(humanScore + "  -  " + aiScore);
    //    }


   //    public void setHeartImages()
   //    {
   //        playerHealth1.setImage(health1);
   //        playerHealth2.setImage(health2);
   //        playerHealth3.setImage(health3);
   //        playerHealth4.setImage(health4);
   //        playerHealth5.setImage(health5);

   //        aiHealth1.setImage(health1);
   //        aiHealth2.setImage(health2);
   //        aiHealth3.setImage(health3);
   //        aiHealth4.setImage(health4);
   //        aiHealth5.setImage(health5);
   //    }
   }

