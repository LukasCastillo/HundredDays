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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author TAU
 */
public class GameScreenController implements Initializable {
    
    private Image pauseImage;
    private Image pauseHoveredImage;
    
    @FXML ImageView pauseButton;
    @FXML ImageView bgImage;
    @FXML Label debugLabel;
    @FXML Label dayLabel;
    @FXML private Label deathDaysLabel;
    
    @FXML Pane bgPane;
    @FXML Pane objectsPane;
    @FXML Pane dayPane;
    @FXML private Pane deathPane;
    @FXML private BorderPane HUDPane;
    
    @FXML StackPane rootPane;
    
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar hungerBar;
    
    @FXML private VBox notificationPane;
    @FXML private Label notificationHeader;
    @FXML private Label notificaitonContent;
    @FXML private Button notificationButton;

    @FXML private void pauseAction(){
        System.out.println("Paused Game");
        HundredDays.getGame().pause();
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
            HundredDays.getGame().unpause();
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
    
    @FXML private void deathExitGame(){
        try {
                //delete current world
                HundredDays.newGame();
                HundredDays.setScreen("HomeScreen");
            } catch (IOException ex) {
                System.out.println("Failed to load stage HomeScreen.fxml");
                System.out.println(ex.getMessage());
                System.exit(1);
            }
    }
    
    @FXML private void settingsMouseEntered(){
        pauseButton.setImage(pauseHoveredImage);
    }
    
    @FXML private void settingsMouseExited(){
        pauseButton.setImage(pauseImage);
    }
    
    @FXML private void exitNotification(){
        HundredDays.getGame().getNotificationHandler().exitNotification();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //disable and enable panes
        deathPane.setVisible(false);
        deathPane.setDisable(true);
        
        HUDPane.setVisible(true);
        HUDPane.setDisable(false);
        
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
        
        HundredDays.getStage().getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent m) -> {
            if(m.getButton() != MouseButton.PRIMARY) return;
            HundredDays.getGame().getPlayerHandler().setAttacking();
        });
        
        rootPane.widthProperty().addListener((ObservableValue<? extends Number> ov, Number oldWidth, Number newWidth) -> {
            HundredDays.getGame().getCamera().setViewW(newWidth.doubleValue());
        });
        
        rootPane.heightProperty().addListener((ObservableValue<? extends Number> ov, Number oldHeight, Number newHeight) -> {
            HundredDays.getGame().getCamera().setViewH(newHeight.doubleValue());
        });
        
        pauseImage = new Image(HundredDays.class.getResource("ui/pause.png").toString());
        pauseHoveredImage = new Image(HundredDays.class.getResource("ui/pause_down.png").toString());
        
        //start game
        HundredDays.getGame().start(this);
        HundredDays.getGame().unpause();
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

    /**
     * @return the healthBar
     */
    public ProgressBar getHealthBar() {
        return healthBar;
    }

    /**
     * @return the hungerBar
     */
    public ProgressBar getHungerBar() {
        return hungerBar;
    }

    /**
     * @return the deathPane
     */
    public Pane getDeathPane() {
        return deathPane;
    }

    /**
     * @return the HUDPane
     */
    public BorderPane getHUDPane() {
        return HUDPane;
    }

    /**
     * @return the deathDaysLabel
     */
    public Label getDeathDaysLabel() {
        return deathDaysLabel;
    }

    /**
     * @return the notificationPane
     */
    public VBox getNotificationPane() {
        return notificationPane;
    }

    /**
     * @return the notificationHeader
     */
    public Label getNotificationHeader() {
        return notificationHeader;
    }

    /**
     * @return the notificaitonContent
     */
    public Label getNotificaitonContent() {
        return notificaitonContent;
    }
}