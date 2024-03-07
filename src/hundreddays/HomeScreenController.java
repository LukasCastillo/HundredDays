/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            HundredDays.setStage("GameScreen.fxml");
        } catch (IOException ex) {
            System.out.println("Failed to load stage GameScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void openSettings(){
        try {
            HundredDays.setStage("SettingsScreen.fxml");
        } catch (IOException ex) {
            System.out.println("Failed to load stage GameScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void exitGame(ActionEvent ae){
        System.out.println("Exiting");
        System.out.println(ae);
        HundredDays.getStage().close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
