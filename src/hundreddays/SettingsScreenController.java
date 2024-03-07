/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class SettingsScreenController implements Initializable {

    @FXML
    private Slider soundSlider;
    @FXML
    private Label soundLabel;
    
    @FXML private void backScreen(){
        try {
            HundredDays.setStage("HomeScreen.fxml");
        } catch (IOException ex) {
            System.out.println("Failed to load stage HomeScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void openControls(){
        try {
            HundredDays.setStage("ControlsScreen.fxml");
        } catch (IOException ex) {
            System.out.println("Failed to load stage ControlsScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        soundLabel.textProperty().bind(
                Bindings.format("%.1f%%", soundSlider.valueProperty())
        );
    }    
    
}
