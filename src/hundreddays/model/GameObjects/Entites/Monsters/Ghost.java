/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Monsters;

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
    public void walk() {
        return;
    }

    @Override
    public void attack() {
        return;
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
    public void render(String screen) {
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
    
}
