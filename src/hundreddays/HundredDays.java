/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays;

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
        //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
        HundredDays.stage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
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
    
}
