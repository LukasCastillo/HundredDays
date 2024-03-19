/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import hundreddays.enums.KeyAction;
import hundreddays.exceptions.KeybindAlreadyExisitsException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;



/**
 * FXML Controller class
 *
 * @author TAU
 */
public class ControlsScreenController implements Initializable {

    private final DualHashBidiMap<KeyAction, Button> buttonKeyMap = new DualHashBidiMap();
    
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
             HundredDays.setScreen("SettingsScreen");
         } catch (IOException ex) {
            System.out.println("Failed to load stage SettingsScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    @FXML
    private void onKeybindPress(KeyEvent ke){
        Button source = (Button)ke.getSource();
        KeyAction action = buttonKeyMap.getKey(source);
        
        assert action != null : "Action is null";
        try {
            HundredDays.getOptions().setKeyBind(action, ke.getCode());
            System.out.println("Keybind Set to: " + ke.getCode());
            source.setTextFill(Color.BLACK);
            source.setText(ke.getCode().toString());
            HundredDays.getStage().getScene().getRoot().requestFocus();
        } catch (KeybindAlreadyExisitsException ex) {
            System.out.println("Keybind " + ke.getCode() + " already set!");
            
            source.setTextFill(Color.RED);
            source.setText("Invalid Key");
            HundredDays.getStage().getScene().getRoot().requestFocus();
            
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonKeyMap.put(KeyAction.MOVE_UP, forwardButton);
        buttonKeyMap.put(KeyAction.MOVE_DOWN, backwardButton);
        buttonKeyMap.put(KeyAction.MOVE_LEFT, leftButton);
        buttonKeyMap.put(KeyAction.MOVE_RIGHT, rightButton);
        
        for(KeyAction ka : KeyAction.values()){
            Button b = buttonKeyMap.get(ka);
            if(b == null) continue;
            b.setText(HundredDays.getOptions().getKeyBind(ka).toString());
        }
    }    
    
}
