/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.GameObjects.Entites.Monsters;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
import hundreddays.model.GameObjects.GameObject;
import hundreddays.model.Hitbox;
import hundreddays.model.Interfaces.Collidable;
import hundreddays.model.Items.Item;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author sethl
 */
public class Zombie extends Monster{
    
    private ImageView zombieView;
    public Hitbox hitbox;
    private Random random;

    public Zombie(double x, double y) {
        super(x, y, "zombie-texture", 90, 80, 
                new Item[] {new Item("Rotten Meat", 2), new Item("Bones")}, 20, 30);
        zombieView = new ImageView(new Image(HundredDays.class.getResource("monsters/zombie.png").toString()));
        hitbox = new Hitbox(0, 0, 10, 20);
        random = new Random();
    }

    @Override
    public void walk(double deltaTime) {
        //move randomrly
    }

    @Override
    public void attack(double deltaTime) {
        if(false){// player within attack range
            //plater.onAttack(baseAttack)
        }else{
            double dX = HundredDays.getGame().getPlayerHandler().getPlayer().getXPos() - xPos;
            double dY = HundredDays.getGame().getPlayerHandler().getPlayer().getYPos() - yPos;
            
            if(Math.abs(dY / dX) < 1 + 0.5 && Math.abs(dY / dX) > 1 - 0.5){
                double magnitude = Math.sqrt(speed * deltaTime * speed * deltaTime * 2);
                this.moveBy(Math.signum(dX) * magnitude, Math.signum(dY) * magnitude);
            }else{
                double magnitude = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
                double angle = Math.atan2(dY, dX);
                double offset = random.nextDouble() - 0.5;
                angle += offset;
                this.moveBy(Math.cos(angle) * speed * deltaTime, Math.sin(angle) * speed * deltaTime);
            }
            
            hitbox.setPosition(xPos, yPos);
            
            for(GameObject go : HundredDays.getGame().getGameObjects()){
//                if(!(go instanceof Collidable)) continue;
//                Collidable co = (Collidable) go;
//                if(!co.getHitbox().intersects(hitbox)) continue;
//                hitbox.pushOut(co.getHitbox());
//                this.setPosition(hitbox.getCenterX(), hitbox.getCenterY());
                
                if(go instanceof Collidable && ((Collidable) go).getHitbox().intersects(hitbox)){
                    hitbox.pushOut(((Collidable) go).getHitbox());
                    this.setPosition(hitbox.getCenterX(), hitbox.getCenterY());
                }else{
                    if(!(go instanceof Zombie)) continue;
                    Zombie z = (Zombie) go;
                    if(!z.hitbox.intersects(hitbox)) continue;
                    hitbox.pushOut(z.hitbox);
                    this.setPosition(hitbox.getCenterX(), hitbox.getCenterY());
                    break;
                }
            }
        }
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
        HundredDays.getGame().getCamera().renderImage(zombieView, xPos, yPos);
        HundredDays.getGame().getCamera().renderHitbox(hitbox);
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
        controller.getObjectsPane().getChildren().add(zombieView);
        controller.getObjectsPane().getChildren().add(hitbox.getRect());
    }

    @Override
    public void dispose(GameScreenController controller) {
        return;
    }
    
}
