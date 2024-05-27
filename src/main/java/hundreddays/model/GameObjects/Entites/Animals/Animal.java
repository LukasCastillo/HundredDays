/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Animals;

import hundreddays.model.GameObjects.Entites.Entity;
import hundreddays.model.Items.Item;
import javafx.scene.image.ImageView;

/**
 *
 * @author sethl
 */
public abstract class Animal extends Entity{
    protected boolean attacked;
    private Item[] drops;
    
    public Animal(double x, double y, ImageView texture, double speed, int hp, Item[] drops) {
        super(x, y, texture, speed, hp);
        this.drops = drops;
        this.attacked = false;
    }

    public abstract void walk();
    public abstract void run();
    
    /**
     * @return the drops
     */
    public Item[] getDrops() {
        return drops;
    }

    /**
     * @return the attacked
     */
    public boolean isAttacked() {
        return attacked;
    }
    
    
    
}
