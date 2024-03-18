/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.Items;

/**
 *
 * @author sethl
 */
public class Weapon extends Item{
    private final String type;
    private final double baseAttack;
    private int durability;
    private final int fullDurability;
    
    public Weapon(String name, int count, String type, double baseAttack, int durability) {
        super(name, count);
        this.type = type;
        this.baseAttack = baseAttack;
        this.durability = durability;
        this.fullDurability = durability;
    }
    
    
    /**
     * Degrades the weapon
     * @return whether the weapon is still usable
     */
    public boolean use(){
        durability--;
        return getDurability() > 0;
    }
    
    public void repair(){
        this.durability = this.getFullDurability();
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the baseAttack
     */
    public double getBaseAttack() {
        return baseAttack;
    }

    /**
     * @return the durability
     */
    public int getDurability() {
        return durability;
    }

    /**
     * @return the fullDurability
     */
    public int getFullDurability() {
        return fullDurability;
    }
    
}
