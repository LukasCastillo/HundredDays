/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Animals;

import hundreddays.model.Items.Item;

/**
 *
 * @author sethl
 */
public class Chicken extends Animal{
    public Chicken(double x, double y) {
        super(x, y, "chicken-texture", 40, 10, new Item[] {new Item("Chicken", 2)});
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
        if(attacked){
            run();
        }else{
            walk();
        }
    }

    @Override
    public void render(String screen) {
        return;
    }

    @Override
    public void onAttack(double damage) {
        this.attacked = true;
    }

    @Override
    public void onHover() {
        return;
    }

    @Override
    public void onUse() {
        return;
    }
    
}
