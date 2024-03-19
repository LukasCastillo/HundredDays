/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class SettingsScreenController implements Initializable {
    private static String previousScreen = "HomeScreen";

    @FXML
    private Slider soundSlider;
    @FXML
    private Label soundLabel;
    @FXML
    private Button fullScreenButton;
    
    @FXML private void backScreen(){
        try {
            HundredDays.setStage(previousScreen);
        } catch (IOException ex) {
            System.out.println("Failed to load stage " + previousScreen);
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void openControls(){
        try {
            HundredDays.setStage("ControlsScreen");
        } catch (IOException ex) {
            System.out.println("Failed to load stage ControlsScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML private void setFullScreen(){
        HundredDays.getStage().setFullScreenExitHint("");
        HundredDays.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        
        if(HundredDays.getOptions().isFullScreen()){
            HundredDays.getStage().setFullScreen(false);
            HundredDays.getOptions().setFullScreen(false);
            fullScreenButton.setText("Windowed");
        }else{
            HundredDays.getStage().setFullScreen(true);
            HundredDays.getOptions().setFullScreen(true);
            fullScreenButton.setText("Fullscreen");
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inilize displays
        soundSlider.setValue(HundredDays.getOptions().getSoundLevel());
        soundLabel.setText(String.format("%.1f%%", HundredDays.getOptions().getSoundLevel()));
        fullScreenButton.setText(HundredDays.getOptions().isFullScreen() ? "Windowed" : "Fullscreen");
        
        soundSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number oldLevel, Number newLevel) -> {
            soundLabel.setText(String.format("%.1f%%", newLevel.doubleValue()));
            HundredDays.getOptions().setSoundLevel(newLevel.doubleValue());
        });
    }    

    /**
     * @param aPreviousScreen the previousScreen to set
     */
    public static void setPreviousScreen(String aPreviousScreen) {
        previousScreen = aPreviousScreen;
    }
}
