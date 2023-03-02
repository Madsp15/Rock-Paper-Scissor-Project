package rps.gui.controller;

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

    @FXML
    private Label labelSelectedZucc, labelSelectedPain, labelSelectedBoulder, labelSelectedCat,
            labelSelectedWallE, labelSelectedDalek, labelSelectedTypeB, labelSelectedHall;
    @FXML
    private ImageView imageZucc, imagePain, imageCat, imageBoulder, imageWallE, imageHall, imageTypeB, imageDalek;
    private Image dalek = new Image("Pictures/Dalek.png");
    private Image hall = new Image("Pictures/HALL 9000.png");
    private Image typeB = new Image("Pictures/YoRHa No.2 Type B.png");
    private Image wallE = new Image("Pictures/WALL-E.png");
    private Image zucc = new Image("Pictures/zucc.png");
    private Image boulder = new Image("Pictures/boulder.png");
    private Image pain = new Image("Pictures/pain.png");
    private Image cat = new Image("Pictures/cat.png");
    private Image imageWithHealth = new Image("Pictures/zucc.png");
    private Image imageWithoutHealth = new Image("Pictures/zucc.png");

    private String humanName = "";
    private String aiName = "";
    private Image humanImage;
    private Image aiImage;


    public void clickZucc(MouseEvent mouseEvent) {
        clearSelectedHuman();
        humanImage = zucc;
        humanName = "The Zucc";
        labelSelectedZucc.setText("Selected");
    }

    public void clickPain(MouseEvent mouseEvent) {
        humanImage = pain;
        clearSelectedHuman();
        humanName = "Harrold of Pain";
        labelSelectedPain.setText("Selected");
    }

    public void clickBoulder(MouseEvent mouseEvent) {
        humanImage = boulder;
        clearSelectedHuman();
        humanName = "The Boulder";
        labelSelectedBoulder.setText("Selected");
    }

    public void clickCat(MouseEvent mouseEvent) {
        humanImage = cat;
        clearSelectedHuman();
        humanName = "Weird Cat";
        labelSelectedCat.setText("Selected");
    }

    public void clickWallE(MouseEvent mouseEvent) {
        clearSelectedAI();
        aiImage = wallE;
        aiName = "WALL-E";
        labelSelectedWallE.setText("Selected");
    }

    public void clickDalek(MouseEvent mouseEvent) {
        aiImage = dalek;
        clearSelectedAI();
        aiName = "Dalek";
        labelSelectedDalek.setText("Selected");
    }

    public void clickTypeB(MouseEvent mouseEvent) {
        aiImage = typeB;
        clearSelectedAI();
        aiName = "YoRHa No.2 Type B";
        labelSelectedTypeB.setText("Selected");
    }

    public void clickHall(MouseEvent mouseEvent) {
        aiImage = hall;
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
            model.setImages(humanImage, aiImage);
            GameViewController controller = loader.getController();
            controller.botSetup(humanName, aiName);
            controller.setGameViewModel(model);
            controller.setAIHumanImage();

            stage.setTitle("RPS: Choose Wisely");
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
        imageZucc.setImage(zucc);
        imageCat.setImage(cat);
        imageBoulder.setImage(boulder);
        imagePain.setImage(pain);
        imageDalek.setImage(dalek);
        imageHall.setImage(hall);
        imageTypeB.setImage(typeB);
        imageWallE.setImage(wallE);


    }

    public void clickWithoutHealth(MouseEvent mouseEvent) {
    }

    public void clickWithHealth(MouseEvent mouseEvent) {
    }
}
