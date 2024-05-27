/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.controllers.GameScreenController;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public abstract class GameObject {
    protected double xPos;
    protected double yPos;
    protected ImageView texture;
    
    public GameObject(double x, double y, ImageView texture){
        this.xPos = x;
        this.yPos = y;
        this.texture = texture;
    }
    
    public abstract void render(GameScreenController controller);
    public abstract void onAttack(double damage);
    public abstract void onHover();
    public abstract void onUse();
    public abstract void initialize(GameScreenController controller);
    public abstract void dispose(GameScreenController controller);

    /**
     * @return the xPos
     */
    public double getXPos() {
        return xPos;
    }

    /**
     * @return the yPos
     */
    public double getYPos() {
        return yPos;
    }
}
