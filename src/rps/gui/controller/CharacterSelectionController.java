package rps.gui.controller;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;
import rps.gui.model.GameViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterSelectionController implements Initializable {

    GameViewController game = new GameViewController();
    @FXML
    private CheckBox checkBoxWithHealth, checkBoxWithoutHealth;

    @FXML
    private Label labelSelectedZucc, labelSelectedPain, labelSelectedBoulder, labelSelectedCat,
            labelSelectedWallE, labelSelectedDalek, labelSelectedTypeB, labelSelectedHall;
    @FXML
    private ImageView imageZucc, imagePain, imageCat, imageBoulder, imageWallE, imageHall, imageTypeB, imageDalek, withHealth, withoutHealth;

    private String humanName = "";
    private String aiName = "";


    public void clickZucc(MouseEvent mouseEvent) {
        clearSelectedHuman();
        humanName = "The Zucc";
        labelSelectedZucc.setText("Selected");
    }

    public void clickPain(MouseEvent mouseEvent) {
        clearSelectedHuman();
        humanName = "Harrold of Pain";
        labelSelectedPain.setText("Selected");
    }

    public void clickBoulder(MouseEvent mouseEvent) {
        clearSelectedHuman();
        humanName = "The Boulder";
        labelSelectedBoulder.setText("Selected");
    }

    public void clickCat(MouseEvent mouseEvent) {
        clearSelectedHuman();
        humanName = "Weird Cat";
        labelSelectedCat.setText("Selected");
    }

    public void clickWallE(MouseEvent mouseEvent) {
        clearSelectedAI();
        aiName = "Wall-E";
        labelSelectedWallE.setText("Selected");
    }

    public void clickDalek(MouseEvent mouseEvent) {
        clearSelectedAI();
        aiName = "Dalek";
        labelSelectedDalek.setText("Selected");
    }

    public void clickTypeB(MouseEvent mouseEvent) {
        clearSelectedAI();
        aiName = "YoRHa No.2 Type B";
        labelSelectedTypeB.setText("Selected");
    }

    public void clickHall(MouseEvent mouseEvent) {
        clearSelectedAI();
        aiName = "HALL 9000";
        labelSelectedHall.setText("Selected");
    }

    public void clickPlay(ActionEvent actionEvent) {
        if(!humanName.equals("") && !aiName.equals("")){
            IPlayer bot = new Player(aiName, PlayerType.AI);
            IPlayer human = new Player(humanName, PlayerType.Human);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/view/GameView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();

            GameViewModel model = new GameViewModel(human, bot);
            GameViewController controller = loader.getController();
            controller.botSetup(bot.getPlayerName());
            controller.setGameViewModel(model);

            stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
            stage.setScene(new Scene(root));

            stage.show();

            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();

        }
    }

    public void clearSelectedHuman(){
        labelSelectedZucc.setText("");
        labelSelectedBoulder.setText("");
        labelSelectedCat.setText("");
        labelSelectedPain.setText("");
    }

    public void clearSelectedAI(){
        labelSelectedDalek.setText("");
        labelSelectedHall.setText("");
        labelSelectedWallE.setText("");
        labelSelectedTypeB.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image dalek = new Image("Pictures/Dalek.png");
        Image hall = new Image("Pictures/HALL 9000.png");
        Image typeB = new Image("Pictures/YoRHa No.2 Type B.png");
        Image wallE = new Image("Pictures/WALL-E.png");
        Image zucc = new Image("Pictures/zucc.png");
        Image boulder = new Image("Pictures/boulder.png");
        Image pain = new Image("Pictures/pain.png");
        Image cat = new Image("Pictures/cat.png");
        Image imageWithHealth = new Image("Pictures/heartred.png");
        Image imageWithoutHealth = new Image("Pictures/heartgrey.png");
        imageZucc.setImage(zucc);
        imageCat.setImage(cat);
        imageBoulder.setImage(boulder);
        imagePain.setImage(pain);
        imageDalek.setImage(dalek);
        imageHall.setImage(hall);
        imageTypeB.setImage(typeB);
        imageWallE.setImage(wallE);
        withHealth.setImage(imageWithHealth);
        withoutHealth.setImage(imageWithoutHealth);
    }

    public void clickWithHealth(MouseEvent mouseEvent) {

        withoutHealth.setScaleX(1);
        withoutHealth.setScaleY(1);
        withHealth.setScaleX(1.2);
        withHealth.setScaleY(1.2);
        game.isHealthModeOn(1);

    }

    public void clickWithoutHealth(MouseEvent mouseEvent) {

        withHealth.setScaleX(1);
        withHealth.setScaleY(1);
        withoutHealth.setScaleX(1.2);
        withoutHealth.setScaleY(1.2);
        game.isHealthModeOn(0);

    }
}
