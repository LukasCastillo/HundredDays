/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.handlers;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
import hundreddays.enums.KeyAction;
import hundreddays.model.Character;
import hundreddays.model.GameObjects.GameObject;
import hundreddays.model.Hitbox;
import hundreddays.model.Interfaces.Collidable;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author TAU
 */
public class PlayerHandler {
    private Character player;
    private Map<KeyAction, Boolean> actionStateMap;
    private double velX, velY;
    private int direction = 0; //0 - up, 1 - left, 2 - down, 3 - right
    private ImageView playerView;
    private Hitbox hitbox;
    private boolean attacking = false;
    
    private final int IMAGE_WIDTH = 48;
    private final int IMAGE_HEIGHT = 48;
    private double timeSinceLastFrame = 0;
    private final double FRAME_TIME = 0.20;
    private final int FRAMES = 6;
    private int frameNo = 0;
    
    public static final double ACCELERATION = 20;
    public static final double MAX_VELOCITY = 300;
    
    public PlayerHandler(Character player){
        this.player = player;
        this.actionStateMap = new HashMap();
        for(KeyAction ka : KeyAction.values()){
            this.actionStateMap.put(ka, false);
        }
        playerView = new ImageView(new Image(HundredDays.class.getResource("player/player.png").toString()));
        playerView.setViewport(new Rectangle2D(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT));
        hitbox = new Hitbox(player.getXPos(), player.getYPos() + 10, 9, 15);
    }
    
    public PlayerHandler(){
        this(new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0));
    }
    
    public void initialize(GameScreenController controller){
        controller.getObjectsPane().getChildren().add(playerView);
        controller.getObjectsPane().getChildren().add(hitbox.getRect());
    }
    
    public void setActionState(KeyAction ke, boolean state){
//        System.out.println("SetAction" + ke + " as " + state);
        actionStateMap.put(ke, state);
    }
    
    public void update(double deltaT){
        double targetX = 0;
        double targetY = 0;
        
        if(actionStateMap.get(KeyAction.MOVE_UP)) targetY += -1;
        if(actionStateMap.get(KeyAction.MOVE_DOWN)) targetY += 1;
        if(actionStateMap.get(KeyAction.MOVE_RIGHT)) targetX += 1;
        if(actionStateMap.get(KeyAction.MOVE_LEFT)) targetX += -1;
        
        if(targetX != 0 || targetY != 0){
            double angle = Math.atan2(targetY, targetX);
        
            targetX = Math.cos(angle);
            targetY = Math.sin(angle);
            
            System.out.println("angle: "  + angle + " mx: " + targetX + " my: " + targetY);
        }
        targetX *= MAX_VELOCITY;
        targetY *= MAX_VELOCITY;
        
        velX += (targetX - velX) * ACCELERATION * deltaT;
        velY += (targetY - velY) * ACCELERATION * deltaT;
        
        velX = Math.signum(velX) * Math.max(0, Math.abs(velX) - 1e-10);
        velY = Math.signum(velY) * Math.max(0, Math.abs(velY) - 1e-10);
        
        if(velX != 0 || velY != 0){
            if(Math.abs(velX) > Math.abs(velY)){
                if(velX > 0) direction = 3;
                else direction = 1;
            }else{
                if(velY < 0) direction = 0;
                else direction = 2;
            }
        }
        
        HundredDays.getGame().getDebugHandler().addDebug("direction", direction);
        
        System.out.println(actionStateMap);
        System.out.println("Position: " + player.getXPos() + " " + player.getYPos());
        System.out.println("Change in pos: " + velX * deltaT + " " + velY * deltaT);
        System.out.println("DeltaT: " + deltaT);
        
        this.moveBy(velX * deltaT, velY * deltaT);
        
        for(GameObject go : HundredDays.getGame().getGameObjects()){
            if(!(go instanceof Collidable)) continue;
            Collidable co = (Collidable) go;
            if(!co.getHitbox().intersects(hitbox)) continue;
            System.out.println("Collided!!!");
            hitbox.pushOut(co.getHitbox());
            player.setPosition(hitbox.getCenterX(), hitbox.getCenterY() - 10);
        }
        
        HundredDays.getGame().getCamera().setCenterX(player.getXPos());
        HundredDays.getGame().getCamera().setCenterY(player.getYPos());
    }
    
    public void render(GameScreenController controller, double deltaT){
        this.timeSinceLastFrame += deltaT;
        if(this.timeSinceLastFrame >= FRAME_TIME){
            this.timeSinceLastFrame = 0;
            this.frameNo = (this.frameNo + 1) % FRAMES;
        }
        
        int frameType = 0;
        
        if(direction == 0) frameType = 2;
        if(direction == 1) frameType = 1;
        if(direction == 2) frameType = 0;
        if(direction == 3) frameType = 1;
        
        if(this.isMoving()) frameType += 3;
        
        
        playerView.setViewport(new Rectangle2D((frameNo * IMAGE_WIDTH), frameType * IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT));
        HundredDays.getGame().getCamera().renderImage(playerView, player.getXPos(), player.getYPos());
        HundredDays.getGame().getCamera().renderHitbox(hitbox);
        if(direction == 1) playerView.scaleXProperty().set(-Math.abs(playerView.scaleXProperty().get()));
        playerView.setViewOrder(-player.getYPos());
        
        controller.getHealthBar().setProgress(player.getHp() / player.getMaxHp());
        controller.getHungerBar().setProgress(player.getHunger() / player.getMaxHunger());
    }
    
    public void setAttacking(){
        attacking = true;
    }
    
    public Character getPlayer(){
        return player;
    }
    
    public boolean isMoving(){
        return Math.abs(velX) > 1e-3 || Math.abs(velY) > 1e-3;
    }
    
    public void moveBy(double dX, double dY){
        player.moveBy(dX, dY);
        hitbox.setPosition(player.getXPos(), player.getYPos() + 10);
    }
    
    public double distanceTo(double x, double y){
        return Math.sqrt(Math.pow(player.getXPos() - x, 2) + Math.pow(player.getYPos() - y, 2));
    }
}
