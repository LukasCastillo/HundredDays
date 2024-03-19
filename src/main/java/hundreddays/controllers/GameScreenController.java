/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import hundreddays.handlers.MapHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class GameScreenController implements Initializable {
    
    @FXML ImageView settingsButton;
    @FXML ImageView bgImage;
    
    
    private MapHandler mapHandler = new MapHandler();

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
                SettingsScreenController.setPreviousScreen("GameScreen");
                HundredDays.setStage("SettingsScreen");
            } catch (IOException ex) {
                System.out.println("Failed to load stage SettingsScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        else if(alert.getResult() == exitButton){
            try {
                HundredDays.setStage("HomeScreen");
            } catch (IOException ex) {
                System.out.println("Failed to load stage HomeScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
    
    @FXML private void onKeyPressed(KeyEvent key){
        System.out.println(key);
        if(key.getCode() == KeyCode.W){
            HundredDays.getGame().player.moveBy(0, 0.5);
            mapHandler.renderMap(bgImage);
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HundredDays.getStage().addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
            System.out.println(key);
            if(key.getCode() == KeyCode.W){
                HundredDays.getGame().player.moveBy(0, 0.5);
            }
            if(key.getCode() == KeyCode.S){        
                HundredDays.getGame().player.moveBy(0, -0.5);
            }
            if(key.getCode() == KeyCode.A){
                HundredDays.getGame().player.moveBy(0.5, 0);
            }
            if(key.getCode() == KeyCode.D){
                HundredDays.getGame().player.moveBy(-0.5, 0);
            }
            
            mapHandler.renderMap(bgImage);
        });
    }    
    
}
