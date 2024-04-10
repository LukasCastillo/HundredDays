/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
import hundreddays.model.Interfaces.Collidable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public class Tree extends GameObject implements Collidable{
    private final String TREE1 = "objects/trees/tree1.png";
    private final String TREE2 = "objects/trees/tree2.png";
    private final String TREE3 = "objects/trees/tree3.png";
    private final String TREE4 = "objects/trees/tree4.png";
    
    ImageView treeView;
    private final int height;

    public Tree(double x, double y, int height) {
        super(x, y, "tree-texture");
        this.height = height;
        
        treeView = new ImageView(new Image(HundredDays.class.getResource(TREE1).toString()));
        System.out.println("Tree");
        System.out.println(treeView);
    }

    @Override
    public void render(GameScreenController controller) {
        treeView.setY(HundredDays.getGame().getPlayerHandler().getPlayer().getYPos() + yPos + HundredDays.getStage().getHeight() / 2);
        treeView.setX(-HundredDays.getGame().getPlayerHandler().getPlayer().getXPos() + xPos + HundredDays.getStage().getWidth()/ 2);
        System.out.println(yPos + " " + xPos);
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
