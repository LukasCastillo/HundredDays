/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Animals;

import hundreddays.controllers.GameScreenController;
import hundreddays.model.Items.Item;

/**
 *
 * @author sethl
 */
public class Cow extends Animal{
    public Cow(double x, double y) {
        super(x, y, null, 20, 30, new Item[] {new Item("Beef")});
    }

    @Override
    public void walk() {
        return;
    }

    @Override
    public void run() {
        return;
    }

    @Override
    public void update(double deltaTime) {
        if(isAttacked()){
            run();
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
        this.attacked = true;
        this.hp -= damage; //add checking if negative later
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
