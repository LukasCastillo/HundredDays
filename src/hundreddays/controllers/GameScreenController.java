/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class GameScreenController implements Initializable {
    
    @FXML ImageView settingsButton;

    @FXML private void openSettings(){
        System.out.println("Open Settings!!");
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Game Paused");
        alert.setHeaderText("Choose an option:");
        
        // Add buttons
        ButtonType continueButton = new ButtonType("Continue");
        ButtonType settingsButton = new ButtonType("Settings");
        ButtonType exitButton = new ButtonType("Exit Game");
        alert.getButtonTypes().setAll(continueButton, settingsButton, exitButton);

        // Show the dialog and wait for a button to be clicked
        alert.showAndWait();
        
        System.out.println("User selected: " + alert.getResult().getText());
        if(alert.getResult() == continueButton){
            
        }
        else if(alert.getResult() == settingsButton){
            try {
                SettingsScreenController.setPreviousScreen("GameScreen.fxml");
                HundredDays.setStage("render/screens/SettingsScreen.fxml");
            } catch (IOException ex) {
                System.out.println("Failed to load stage SettingsScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        else if(alert.getResult() == exitButton){
            try {
                HundredDays.setStage("render/screens/HomeScreen.fxml");
            } catch (IOException ex) {
                System.out.println("Failed to load stage HomeScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
