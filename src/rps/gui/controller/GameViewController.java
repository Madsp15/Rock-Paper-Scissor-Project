package rps.gui.controller;

// Java imports
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    private Label lablePlayerName, lableAIName, lableScore;
    @FXML
    private ImageView pictureActiveRPCPlayer, pictureActiveRPCAI, pictureAI;
    @FXML
    private MFXTextField textAI;
    @FXML
    private ImageView imageRockButton, imagePaperButton, imageScissorButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
