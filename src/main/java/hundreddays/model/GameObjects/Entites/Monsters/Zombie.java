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
import java.util.Date;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author sethl
 */
public class Zombie extends Monster{
    
    public final static double ATTACK_COOLDOWN = 1;
    public final static double FIRE_COOLDOWN = 1;
    
    private Hitbox hitbox;
    private Random random;
    
    private double lastAttackTime = 0;
    private double lastFireTime = 0;

    public Zombie(double x, double y) {
        super(x, y, new ImageView(new Image(HundredDays.class.getResource("monsters/zombie.png").toString())), 30, 80, 
                new Item[] {new Item("Wood")}, 20, 30);
        hitbox = new Hitbox(0, 0, 10, 20);
        random = new Random();
    }

    @Override
    public void walk(double deltaTime) {
        
    }

    @Override
    public void attack(double deltaTime) {
        if(HundredDays.getGame().getPlayerHandler().distanceTo(this.xPos, this.yPos) < 10){// player within attack range
            //plater.onAttack(baseAttack)
            if((new Date().getTime())/1000 - lastAttackTime < ATTACK_COOLDOWN) return;
            System.out.println("NEAR!!!!!");
            HundredDays.getGame().getPlayerHandler().getPlayer().onAttack(baseAttack);
            lastAttackTime = (new Date().getTime())/1000;
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
                if(go instanceof Collidable && ((Collidable) go).getHitbox().intersects(hitbox)){
                    hitbox.pushOut(((Collidable) go).getHitbox());
                    this.setPosition(hitbox.getCenterX(), hitbox.getCenterY());
                }else{
                    if(!(go instanceof Zombie)) continue;
                    Zombie z = (Zombie) go;
                    if(z == this) continue;
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
        if(!HundredDays.getGame().isNight()){
            lastFireTime += deltaTime;
            if(lastFireTime >= FIRE_COOLDOWN){
                lastFireTime = 0;
                onAttack(10);
            }
        }
        
        
        if(HundredDays.getGame().getPlayerHandler().distanceTo(this.xPos, this.yPos) < 800 || true){
            attack(deltaTime);
        }else{
            walk(deltaTime);
        }
    }

    @Override
    public void render(GameScreenController controller) {
        HundredDays.getGame().getCamera().renderImage(this.texture, xPos, yPos);
        HundredDays.getGame().getCamera().renderHitbox(hitbox);
    }

    @Override
    public void onAttack(double damage) {
        this.hp -= damage * (1 - this.baseDefence / 1000);
//        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue()));
        System.out.println("Help!!: " + this.hp);
        if(this.hp <= 0) {
            HundredDays.getGame().deleteGameObject(this);
            for(Item item : this.getDrops()){
                HundredDays.getGame().getPlayerHandler().getPlayer().pickUpItem(item);
            }
        }
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
        controller.getObjectsPane().getChildren().add(this.texture);
        controller.getObjectsPane().getChildren().add(hitbox.getRect());
    }

    @Override
    public void dispose(GameScreenController controller) {
        controller.getObjectsPane().getChildren().remove(this.texture);
        controller.getObjectsPane().getChildren().remove(hitbox.getRect());
    }
    
}
