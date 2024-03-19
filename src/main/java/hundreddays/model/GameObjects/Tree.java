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
public class Tree extends GameObject implements Collidable{
    private final int height;

    public Tree(double x, double y, int height) {
        super(x, y, "tree-texture");
        this.height = height;
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
    public boolean collidesWith(double[] hitbox) {
        return false;
    }
    
}
