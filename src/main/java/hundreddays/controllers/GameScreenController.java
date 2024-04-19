/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import hundreddays.enums.KeyAction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class GameScreenController implements Initializable {
    
    @FXML ImageView settingsButton;
    @FXML ImageView bgImage;
    @FXML Label debugLabel;
    @FXML Label dayLabel;
    
    @FXML Pane bgPane;
    @FXML Pane objectsPane;
    @FXML Pane dayPane;
    @FXML StackPane rootPane;

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
                HundredDays.setScreen("SettingsScreen");
            } catch (IOException ex) {
                System.out.println("Failed to load stage SettingsScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        else if(alert.getResult() == exitButton){
            try {
                //stop game
                HundredDays.getGame().close();
                HundredDays.setScreen("HomeScreen");
            } catch (IOException ex) {
                System.out.println("Failed to load stage HomeScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //on window key down
        HundredDays.getStage().addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent key) -> {
            //            System.out.println(key);
            KeyAction action = HundredDays.getOptions().getAssociatedKeyAction(key.getCode());
            
            System.out.println("Pressed: " + action);
            if(key.getCode() == KeyCode.Z) HundredDays.getGame().getCamera().zoom(0.1);
            if(key.getCode() == KeyCode.X) HundredDays.getGame().getCamera().zoom(-0.1);
            
            if(action != null){
                HundredDays.getGame().getPlayerHandler().setActionState(action, true);
            }
        });
        
        //on window key released
        HundredDays.getStage().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent key) -> {
            System.out.println("Relased");
//            System.out.println(key);
            KeyAction action = HundredDays.getOptions().getAssociatedKeyAction(key.getCode());
            
            if(action != null){
                HundredDays.getGame().getPlayerHandler().setActionState(action, false);
            }
        });
        
        rootPane.widthProperty().addListener((ObservableValue<? extends Number> ov, Number oldWidth, Number newWidth) -> {
            HundredDays.getGame().getCamera().setViewW(newWidth.doubleValue());
        });
        
        rootPane.heightProperty().addListener((ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) -> {
            HundredDays.getGame().getCamera().setViewH(newHeight.doubleValue());
        });
        
        //start game
        HundredDays.getGame().start(this);
    }
    
    public ImageView getBgImage(){
        return bgImage;
    }
    
    public Label getDebugLabel(){
        return debugLabel;
    }
    
    public Pane getObjectsPane(){
        return objectsPane;
    }
    
    public Label getDayLabel(){
        return dayLabel;
    }
    
    public Pane getDayPane(){
        return dayPane;
    }
}