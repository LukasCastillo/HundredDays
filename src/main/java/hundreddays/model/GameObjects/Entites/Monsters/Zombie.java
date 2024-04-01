/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Monsters;

import hundreddays.controllers.GameScreenController;
import hundreddays.model.Items.Item;

/**
 *
 * @author sethl
 */
public class Zombie extends Monster{

    public Zombie(double x, double y) {
        super(x, y, "zombie-texture", 30, 80, 
                new Item[] {new Item("Rotten Meat", 2), new Item("Bones")}, 20, 30);
    }

    @Override
    public void walk() {
        //move randomrly
    }

    @Override
    public void attack() {
        if(true){// player within attack range
            //plater.onAttack(baseAttack)
        }else{
            //move towards player
        }
    }

    @Override
    public void update(double deltaTime) {
        if(true){ //player nearby
            attack();
        }else{
            walk();
        }
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
    
}
