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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/view/CharacterSelection.fxml"));
        Parent root = loader.load();

        stage.setTitle("RPS: Choose Wisely");
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
