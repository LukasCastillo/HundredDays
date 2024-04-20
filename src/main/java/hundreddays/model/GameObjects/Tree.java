/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
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
        STUMP("tree4.png"), TALL("tree3.png"), BUSH("tree2.png"), REGULAR("tree1.png");
        
        public final String IMG_PATH;

        private TreeTypes(String path) {
            this.IMG_PATH = path;
        }
    }
    
    ImageView treeView;
    private final int height;
    private final TreeTypes type;

    public Tree(double x, double y, int height, TreeTypes type) {
        super(x, y, "tree-texture");
        this.height = height;
        this.type = type;
        
        treeView = new ImageView(new Image(HundredDays.class.getResource("objects/trees/" + type.IMG_PATH).toString()));
        treeView.smoothProperty().set(false);
    }
    
    //generate random tree
    public Tree(double x, double y, int height){
        this(x, y, height, TreeTypes.values()[(new Random()).nextInt(TreeTypes.values().length)]);
    }

    @Override
    public void render(GameScreenController controller) {
        HundredDays.getGame().getCamera().renderImage(treeView, xPos, yPos);
        treeView.setViewOrder(-yPos);
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
    public boolean collidesWith(double[] hitbox) {
        return false;
    }

    @Override
    public void initialize(GameScreenController controller) {
        controller.getObjectsPane().getChildren().add(treeView);
        System.out.println("Initlized treee");
    }

    @Override
    public void dispose(GameScreenController controller) {
        controller.getObjectsPane().getChildren().remove(treeView);
        System.out.println("Initlized treee");
    }
    
}
