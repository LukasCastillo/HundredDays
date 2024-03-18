/*
 *  Tau Lukas Castillo
 */
package hundreddays.model;

import hundreddays.model.Items.Item;
import hundreddays.model.Items.Food;
import hundreddays.model.GameObjects.Entites.Entity;
import hundreddays.model.Items.Weapon;
import java.util.ArrayList;

/**
 *
 * @author sethl
 */
public class Character {
    private String name;
    private double hp, hunger;
    private double maxHp, maxHunger;
    private double baseAttack, baseDefence;
    private String role;
    private ArrayList<Item> items;
    private double xPos, yPos;
    private Item selectedItem;
    
    public Character(String n, double hp, double hg, double bAtk, 
            double bDef, String role, double xPos, double yPos){
        this.name = n;
        this.hp = hp;
        this.hunger = hg;
        this.maxHp = hp;
        this.maxHunger = hg;
        this.baseAttack = bAtk;
        this.baseDefence = bDef;
        this.role = role;
        this.xPos = xPos;
        this.yPos = yPos;
        this.selectedItem = null;
        this.items = new ArrayList();
    }
    
    public void attack(Entity e){
        if(this.selectedItem instanceof Weapon){
            e.onAttack(baseAttack + ((Weapon) this.selectedItem).getBaseAttack());
            ((Weapon) this.selectedItem).use();
        }else{
            e.onAttack(baseAttack);
        }
    }
    
    public void useItem(){
        if(this.selectedItem instanceof Food){
            this.hunger += ((Food) this.selectedItem).getFoodPoints();
            if(this.hunger > this.maxHunger) this.hunger = this.maxHunger;
            
            ((Food) this.selectedItem).addCount(1);
        }
        //add more cases when more types of items are put
    }
    
    public void update(double deltaTime){
        
    }
    
    public void onAttack(double damage){
        this.hp -= damage * this.baseDefence / 10;
    }
    
    public void render(String s){
        
    }
    
    public void pickUpItem(Item item){
        for(Item i : items){
            if(i.getName().equals(item.getName())){
                i.addCount(item.getCount());
                return;
            }
        }
        
        if(this.items.size() >= 20) return; //throw the InventoryFullException
        this.items.add(item);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the hp
     */
    public double getHp() {
        return hp;
    }

    /**
     * @return the hunger
     */
    public double getHunger() {
        return hunger;
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

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @return the xPos
     */
    public double getXPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public double getYPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
    
    /**
     * @param dX change in x
     * @param dY change in y
     */
    public void moveBy(double dX, double dY){
        this.xPos += dX;
        this.yPos += dY;
    }

    /**
     * @return the selectedItem
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
    
    
}
