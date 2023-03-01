package rps.gui.controller;

// Java imports
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image chatBox = new Image("Pictures/speech_bubble.png");
        Image versus = new Image("Pictures/versus.png");
        Image rock = new Image("Pictures/rock.png");
        Image paper = new Image("Pictures/paper.png");
        Image scissors = new Image("Pictures/scissors.png");
        imageChatBox.setImage(chatBox);
        imageVersus.setImage(versus);
        imageRockButton.setImage(rock);
        imagePaperButton.setImage(paper);
        imageScissorButton.setImage(scissors);
    }

    public void clickRock(MouseEvent mouseEvent) {
    }

    public void clickPaper(MouseEvent mouseEvent) {
    }

    public void clickScissor(MouseEvent mouseEvent) {
    }
}
