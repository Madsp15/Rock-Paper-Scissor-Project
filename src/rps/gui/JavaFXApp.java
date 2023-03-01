package rps.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rps.bll.game.GameManager;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;
import rps.bll.game.*;
import rps.bll.player.*;
import java.util.Random;

/**
 * JavaFX implementation of the RPS game
 *
 * @author smsj
 */
public class JavaFXApp extends Application {

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/rps/gui/view/GameView.fxml"));
        stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
        stage.setScene(new Scene(root));
        stage.show();

    }


    IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);
    String playerName = "Player1";
    IPlayer human = new Player(playerName, PlayerType.Human);


    GameManager ge = new GameManager(human, bot);
    private String getRandomBotName() {
        String[] botNames = new String[] {
                "WALL-E",
                "Dalek",
                "YoRHa No.2 Type B",
                "HALL 9000"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }
    private String displayStartOfGameMessage(String botName) {
        if(botName.equals("WALL-E")){
           return "Pleased to meet you";
        }
        if(botName.equals("Dalek")){
            return "You will be exterminated!";
        }
        if(botName.equals("YoRHa No.2 Type B")){
            return "Glory to Mankind";
        }
        if(botName.equals("HALL 9000")){
            return "I am the H.A.L 9000. You may call me Hal";
        }
        return "I don't know what to say...";
    }
    private String displayGoodMoveMessage(String botName) {
        if(botName.equals("WALL-E")){
            return "Well played!";
        }
        if(botName.equals("Dalek")){
            return "Exterminate!";
        }
        if(botName.equals("YoRHa No.2 Type B")){
            return "I underestimated you";
        }
        if(botName.equals("HALL 9000")){
            return "The 9000 series is the most reliable computer ever made.";
        }
        return "I don't know what to say...";
    }
    private String displayBadMoveMessage(String botName) {
        if(botName.equals("WALL-E")){
            return "Next time you will win";
        }
        if(botName.equals("Dalek")){
            return "How could you think that was a good move?";
        }
        if(botName.equals("YoRHa No.2 Type B")){
            return "Mission sucessful";
        }
        if(botName.equals("HALL 9000")){
            return "Why are you doing this to me?";
        }
        return "I don't know what to say...";
    }

}
