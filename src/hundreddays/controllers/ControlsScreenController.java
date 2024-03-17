/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class ControlsScreenController implements Initializable {

    @FXML
    private Button forwardButton;
    @FXML
    private Button backwardButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    
    
    @FXML private void backScreen(){
         try {
             HundredDays.setStage("render/screens/SettingsScreen.fxml");
         } catch (IOException ex) {
            System.out.println("Failed to load stage SettingsScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML
    private void onKeybindPress(KeyEvent ke){
        Button source = (Button)ke.getSource();
        System.out.println("Keybind Set to: " + ke.getCode());
        source.setText(ke.getCode().toString());
        HundredDays.getStage().getScene().getRoot().requestFocus();
    }

    private void setKeybind(Button source, KeyCode code){
        if(source == forwardButton) System.out.println("change options");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
