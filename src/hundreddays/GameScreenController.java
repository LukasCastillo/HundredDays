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

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class GameScreenController implements Initializable {
    
    @FXML Button settingsButton;

    @FXML private void openSettings(ActionEvent ae){
        System.out.println("Open Settings!!");
        System.out.println(ae);
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
