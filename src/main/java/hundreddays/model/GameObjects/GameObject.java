/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

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
    
    public abstract void render(String screen); //placeholder for screen object
    public abstract void onAttack(double damage);
    public abstract void onHover();
    public abstract void onUse();

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
