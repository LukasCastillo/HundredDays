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
        System.out.println(HundredDays.getGame().getPlayerHandler().getPlayer().getYPos());
        
        
        
        bgImage.translateYProperty().set(HundredDays.getGame().getPlayerHandler().getPlayer().getYPos());
        bgImage.translateXProperty().set(-HundredDays.getGame().getPlayerHandler().getPlayer().getXPos());

    }
    
}
