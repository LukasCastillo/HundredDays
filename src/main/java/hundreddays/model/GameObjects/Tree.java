/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
import hundreddays.model.Hitbox;
import hundreddays.model.Interfaces.Collidable;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public class Tree extends GameObject implements Collidable{
    public static enum TreeTypes{
        STUMP("tree4.png", new Hitbox(0, 0, 14, 10), 0, 0),
        TALL("tree3.png", new Hitbox(0, 0, 14, 24), 0, 6),
        BUSH("tree2.png", new Hitbox(0, 0, 0, 0), 0, 0),
        REGULAR("tree1.png", new Hitbox(0, 0, 14, 24), 0, 6);
        
        public final String IMG_PATH;
        public final Hitbox hitbox;
        public final double xHOff;
        public final double yHOff;

        private TreeTypes(String path, Hitbox hitbox, double x, double y) {
            this.IMG_PATH = path;
            this.hitbox = hitbox;
            this.xHOff = x;
            this.yHOff = y;
        }
    }
    
    private final int height;
    private final TreeTypes type;
    private Hitbox hitbox;

    public Tree(double x, double y, int height, TreeTypes type) {
        super(x, y, new ImageView(new Image(HundredDays.class.getResource("objects/trees/" + type.IMG_PATH).toString())));
        this.height = height;
        this.type = type;
        this.hitbox = type.hitbox.copy();
        
        this.texture.smoothProperty().set(false);
    }
    
    //generate random tree
    public Tree(double x, double y, int height){
        this(x, y, height, TreeTypes.values()[(new Random()).nextInt(TreeTypes.values().length)]);
    }

    @Override
    public void render(GameScreenController controller) {
        HundredDays.getGame().getCamera().renderImage(this.texture, xPos, yPos);
        texture.setViewOrder(-yPos);
        
        hitbox.setPosition(xPos + type.xHOff, yPos + type.yHOff);
        HundredDays.getGame().getCamera().renderHitbox(hitbox);
//        System.out.println(yPos + " " + xPos);
    }

    @Override
    public void onAttack(double damage) {
        return;
    }

    @Override
    public void onHover() {
        return;
    }

    @Override
    public void onUse() {
        return;
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    @Override
    public void initialize(GameScreenController controller) {
        controller.getObjectsPane().getChildren().add(texture);
        controller.getObjectsPane().getChildren().add(hitbox.getRect());
        System.out.println("Initlized treee");
    }

    @Override
    public void dispose(GameScreenController controller) {
        controller.getObjectsPane().getChildren().remove(texture);
        System.out.println("Initlized treee");
    }
    
}
