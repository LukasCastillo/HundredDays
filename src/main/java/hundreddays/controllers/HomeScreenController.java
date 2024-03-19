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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class HomeScreenController implements Initializable {
    private Stage stage;
    
    @FXML Button startButton;
    @FXML Button exitButton;
    
    @FXML private void openGame(ActionEvent ae){
        try {
            HundredDays.setScreen("GameScreen");
        } catch (IOException ex) {
            System.out.println("Failed to load stage GameScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void openSettings(){
        try {
            SettingsScreenController.setPreviousScreen("HomeScreen");
            HundredDays.setScreen("SettingsScreen");
        } catch (IOException ex) {
            System.out.println("Failed to load stage GameScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void exitGame(ActionEvent ae){
        System.out.println("Exiting");
        HundredDays.saveOptions();
        HundredDays.getStage().close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
