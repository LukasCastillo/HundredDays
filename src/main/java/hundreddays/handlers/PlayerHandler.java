/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.handlers;

import hundreddays.enums.KeyAction;
import hundreddays.model.Character;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TAU
 */
public class PlayerHandler {
    private Character player;
    private Map<KeyAction, Boolean> actionStateMap;
    private double velX, velY;
    
    public static final double ACCELERATION = 20;
    public static final double MAX_VELOCITY = 60;
    
    public PlayerHandler(Character player){
        this.player = player;
        this.actionStateMap = new HashMap();
        for(KeyAction ka : KeyAction.values()){
            this.actionStateMap.put(ka, false);
        }
    }
    
    public PlayerHandler(){
        this(new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0));
    }
    
    public void setActionState(KeyAction ke, boolean state){
//        System.out.println("SetAction" + ke + " as " + state);
        actionStateMap.put(ke, state);
    }
    
    public void update(double deltaT){
        double targetX = 0;
        double targetY = 0;
        
        if(actionStateMap.get(KeyAction.MOVE_UP)) targetY += 1;
        if(actionStateMap.get(KeyAction.MOVE_DOWN)) targetY += -1;
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
        
        System.out.println(actionStateMap);
        System.out.println("Position: " + player.getXPos() + " " + player.getYPos());
        System.out.println("Change in pos: " + velX * deltaT + " " + velY * deltaT);
        System.out.println("DeltaT: " + deltaT);
        
        player.moveBy(velX * deltaT, velY * deltaT);
    }
    
    public void render(){
        
    }
    
    public Character getPlayer(){
        return player;
    }
    
}
