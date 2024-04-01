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
public class Pig extends Animal{

    public Pig(double x, double y) {
        super(x, y, "pig-texture", 30, 20, new Item[] {new Item("Pork")});
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
        this.attacked = false;
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
