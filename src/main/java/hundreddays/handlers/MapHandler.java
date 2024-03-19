/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.handlers;

import hundreddays.HundredDays;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public class MapHandler {
    
    public void renderMap(ImageView bgImage){
        System.out.println(HundredDays.getGame().player.getYPos());
        bgImage.translateYProperty().set(HundredDays.getGame().player.getYPos()*10);
        bgImage.translateXProperty().set(HundredDays.getGame().player.getXPos()*10);

    }
    
}
