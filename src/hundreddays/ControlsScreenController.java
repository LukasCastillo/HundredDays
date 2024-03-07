/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class ControlsScreenController implements Initializable {
    
    
    @FXML private void backScreen(){
         try {
            HundredDays.setStage("SettingsScreen.fxml");
        } catch (IOException ex) {
            System.out.println("Failed to load stage SettingsScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
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
