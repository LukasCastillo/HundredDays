package hundreddays;

import hundreddays.game.Game;
import hundreddays.game.Options;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Pane;

/**
 * JavaFX HundredDays
 */
public class HundredDays extends Application {
    private static Options options;
    private static Game game;
    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        HundredDays.stage = stage;
        stage.setTitle("100 Days");
        
        scene = new Scene(new Pane(), 600, 400);
        stage.setScene(scene);
        setScreen("HomeScreen");
        stage.show();
        
        game = new Game();
        options = new Options();
    }

    
    public static Stage getStage(){
        return stage;
    }
    
    public static Game getGame(){
        return game;
    }
    
    public static Options getOptions(){
        return options;
    }
    
    /**
     *
     * @param path path to the fxml document
     * @return Returns the associated controller of the screen
     * @throws IOException
     */
    public static Object setScreen(String path) throws IOException{
        System.out.println("Opened " + path + " screen");
        FXMLLoader loader = new FXMLLoader(HundredDays.class.getResource("screens/" + path + ".fxml"));
        stage.getScene().setRoot(loader.load());
        return loader.getController();
    }

    public static void main(String[] args) {
        launch();
    }

}