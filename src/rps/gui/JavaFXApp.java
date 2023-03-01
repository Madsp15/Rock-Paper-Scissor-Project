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
                "Wallee",
                "Darlek",
                "YoRHa No.2 Type B",
                "HALL 9000"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }
}
