/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.controllers.GameScreenController;

/**
 *
 * @author TAU
 */
public abstract class GameObject {
    protected double xPos;
    protected double yPos;
    protected String texture; // placeholder for texture object
    
    public GameObject(double x, double y, String texture){
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
