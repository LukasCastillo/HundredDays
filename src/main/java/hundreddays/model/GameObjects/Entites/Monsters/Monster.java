/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Monsters;

import hundreddays.model.GameObjects.Entites.Entity;
import hundreddays.model.Items.Item;

/**
 *
 * @author sethl
 */
public abstract class Monster extends Entity {
    private Item[] drops;
    protected double baseAttack;
    protected double baseDefence;
    
    public Monster(double x, double y, String texture, double speed, int hp, Item[] drops, double bAtk, double bDef) {
        super(x, y, texture, speed, hp);
        this.drops = drops;
        this.baseAttack = bAtk;
        this.baseDefence = bDef;
    }
    
    public abstract void walk();
    public abstract void attack();

    /**
     * @return the drops
     */
    public Item[] getDrops() {
        return drops;
    }

    /**
     * @return the baseAttack
     */
    public double getBaseAttack() {
        return baseAttack;
    }

    /**
     * @return the baseDefence
     */
    public double getBaseDefence() {
        return baseDefence;
    }
    
}
