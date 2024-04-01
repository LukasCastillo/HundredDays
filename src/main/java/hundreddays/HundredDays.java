package hundreddays;

import com.google.gson.Gson;
import hundreddays.game.Game;
import hundreddays.game.Options;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * JavaFX HundredDays
 */
public class HundredDays extends Application {
    private static Options options;
    private static Game game;
    private static Stage stage;
    private static Scene scene;
    private static Object controller;

    //if project wont load build and clean first to init libraries
    @Override
    public void start(Stage stage) throws IOException {
        HundredDays.stage = stage;
        stage.setTitle("100 Days");
        
        scene = new Scene(new Pane(), 600, 400);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        setScreen("HomeScreen");
        stage.show();
        
        game = new Game();
        options = new Options();
        
        //load options
        String optionsString = readFileContents(new File("Hundred_Days/options.json"));
        if(optionsString != null){
            System.out.println(optionsString);
            Gson gson = new Gson();
            options = gson.fromJson(optionsString, Options.class);
        }
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
    
    public static Object getController(){
        return controller;
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
        controller = loader.getController();
        System.out.println(controller);
        return controller;
    }
    
    public static void saveOptions(){
        Gson gson = new Gson();
        String serilizedOptions = gson.toJson(options);
        
        try {
            File optionsFile = new File("Hundred_Days/options.json");
            optionsFile.getParentFile().mkdirs();
            optionsFile.createNewFile();
            FileWriter fw = new FileWriter(optionsFile);
            fw.write(serilizedOptions);
            fw.close();
            
            System.out.println("Saved options to:");
            System.out.println(optionsFile.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Not able to save options file!");
            System.out.println(ex);
        }
        
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    private static String readFileContents(File file){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Uable to read file: " + file.getAbsolutePath());
        }
        
        return null;
    }
    
    @Override
    public void stop(){
        System.out.println("Stage is closing");
        HundredDays.saveOptions();
        HundredDays.getGame().close();
    }

}