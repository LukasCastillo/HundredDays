/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects;

import hundreddays.controllers.GameScreenController;
import hundreddays.model.Hitbox;
import hundreddays.model.Interfaces.Collidable;

/**
 *
 * @author TAU
 */
public class Rock extends GameObject implements Collidable{
    
    private final int height, width;

    public Rock(double x, double y, int h, int w) {
        super(x, y, null);
        this.height = h;
        this.width = w;
    }

    @Override
    public void render(GameScreenController controller) {
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
    public void initialize(GameScreenController controller) {
        return;
    }

    @Override
    public void dispose(GameScreenController controller) {
        return;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }
    
    
}
