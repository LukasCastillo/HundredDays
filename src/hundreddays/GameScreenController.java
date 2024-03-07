/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
