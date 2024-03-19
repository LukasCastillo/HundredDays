package hundreddays;

import hundreddays.game.Game;
import hundreddays.game.Options;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        
        //scene = new Scene(loadFXML("primary"), 640, 480);
        setStage("HomeScreen");
        stage.show();
        
        game = new Game();
        options = new Options();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
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
    public static Object setStage(String path) throws IOException{
        System.out.println("Opened " + path + " screen");
        FXMLLoader loader = new FXMLLoader(HundredDays.class.getResource("screens/" + path + ".fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        return loader.getController();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HundredDays.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}