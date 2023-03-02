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
import rps.gui.controller.GameViewController;
import rps.gui.model.GameViewModel;

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
        String playerName = "Player1";
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);
        IPlayer human = new Player(playerName, PlayerType.Human);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/view/GameView.fxml"));
        Parent root = loader.load();

        GameViewModel model = new GameViewModel(human, bot);
        GameViewController controller = loader.getController();
        controller.botSetup(bot.getPlayerName());
        controller.setGameViewModel(model);

        stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
        stage.setScene(new Scene(root));

        stage.show();

    }
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


}
