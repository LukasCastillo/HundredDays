/*
 *  Tau Lukas Castillo
 */
package hundreddays.controllers;

import hundreddays.HundredDays;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author sethl
 */
public class LoreScreenController implements Initializable{
    @FXML private void openHome(){
        try {
            HundredDays.setScreen("HomeScreen");
        } catch (IOException ex) {
            System.out.println("Failed to load stage HomeScreen.fxml");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        return;
    }
}
