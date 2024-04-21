/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects.Entites;

import hundreddays.controllers.GameScreenController;
import hundreddays.model.Hitbox;
import hundreddays.model.Interfaces.Collidable;
import hundreddays.model.Items.Item;
import java.util.ArrayList;

/**
 *
 * @author TAU
 */
public class Base extends Entity implements Collidable{
    private String name;
    private double baseAttack, baseDefence;
    private int regenSpeed;
    private ArrayList<Item> inventory;
    private int capacity;
    private int level;
    
    public Base(double x, double y, String name, double bAtk, double bDef, int regenSpeed, int capacity, int level) {
        super(x, y, "base-texture", 0, 300);
        
        this.name = name;
        this.baseAttack = bAtk;
        this.baseDefence = bDef;
        this.regenSpeed = regenSpeed;
        this.inventory  = new ArrayList();
        this.capacity = capacity;
        this.level = level;
    }
    
    public void storeItem(Item item){
        for(Item i : getInventory()){
            if(i.getName().equals(item.getName())){
                i.addCount(item.getCount());
                return;
            }
        }
        
        if(this.getInventory().size() >= this.capacity) return; //throw the InventoryFullException
        this.getInventory().add(item);
    }
    
    public void repairBase(){
        this.hp++;
    }

    @Override
    public void render(GameScreenController controller) {
        return;
    }

    @Override
    public void onAttack(double damage) {
        this.hp -= damage * (1 - this.baseDefence / 1000);
    }

    @Override
    public void onHover() {
        return;
    }

    @Override
    public void onUse() {
        //show inventory screen
    }

    @Override
    public void update(double deltaTime) {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
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
