/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.handlers;

import hundreddays.HundredDays;
import hundreddays.model.GameObjects.Tree;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public class MapHandler {
    
    public final static double MAP_SIZE = 1600;
    
    public void initialize(ImageView bgImage){
        bgImage.setImage(new Image(HundredDays.class.getResource("bg/map.png").toString(), MAP_SIZE, MAP_SIZE, true, false));
        bgImage.setFitHeight(MAP_SIZE);
        bgImage.setFitWidth(MAP_SIZE);
        
        
        Random random = new Random();
        for(int i = 0; i < 300; i++){
            HundredDays.getGame().addGameObject(new Tree(random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16, random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16, 1));
        }
    }
    
    public void renderMap(ImageView bgImage){
        System.out.println(HundredDays.getGame().getPlayerHandler().getPlayer().getYPos());
        
        HundredDays.getGame().getCamera().renderImage(bgImage, MAP_SIZE / 2, MAP_SIZE / 2);

    }
    
}
