/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.model.Interfaces.Collidable;

/**
 *
 * @author TAU
 */
public class Rock extends GameObject implements Collidable{
    private final int height, width;

    public Rock(double x, double y, int h, int w) {
        super(x, y, "rock-texure");
        this.height = h;
        this.width = w;
    }

    @Override
    public void render(String screen) {
        return;
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
    public boolean collidesWith(double[] h) {
        return h[0] + h[2] >= this.xPos && h[0] <= this.xPos + this.width 
                && h[1] + h[3] >= this.yPos && h[1] <= this.yPos + this.height;
        
        // placeholder for hitbox collision check
    }
    
    
}
