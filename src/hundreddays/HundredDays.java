/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TAU
 */
public class HundredDays extends Application {
    private static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        //navigation buttons work
        HundredDays.stage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("render/screens/HomeScreen.fxml"));
        stage.setTitle("Hundred Days");
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        System.out.println("Help!!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }
    
    /**
     *
     * @param path path to the fxml document
     * @return Returns the associated controller of the screen
     * @throws IOException
     */
    public static Object setStage(String path) throws IOException{
        System.out.println("Opened " + path + " screen");
        FXMLLoader loader = new FXMLLoader(HundredDays.class.getResource(path));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        return loader.getController();
    }
}
