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
public class Ghost extends Monster{

    public Ghost(double x, double y, String texture) {
        super(x, y, "ghost-texture", 50, 90, new Item[] {}, 10, 60);
    }

    @Override
    public void walk(double deltaTime) {
        return;
    }

    @Override
    public void attack(double deltaTime) {
        return;
    }

    @Override
    public void update(double deltaTime) {
        if(true){ //player nearby
            attack(deltaTime);
        }else{
            walk(deltaTime);
        }
    }

    @Override
    public void render(GameScreenController controller) {
        return;
    }

    @Override
    public void onAttack(double damage) {
        this.hp -= damage * this.baseDefence / 10;
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
