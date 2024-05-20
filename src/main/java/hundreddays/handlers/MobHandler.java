/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.handlers;

import hundreddays.HundredDays;
import hundreddays.model.GameObjects.Entites.Monsters.Monster;
import hundreddays.model.GameObjects.Entites.Monsters.Zombie;
import hundreddays.model.GameObjects.GameObject;
import java.util.Random;

/**
 *
 * @author TAU
 */
public class MobHandler {
    
    public int MOB_CAP = 4;
    public static final double SPAWN_PERIOD = 0.5;
    private double lastSpawn = 0;
    private Random random;
    
    public MobHandler(){
        this.random = new Random();
    }
    
    public void update(double deltaTime){
        MOB_CAP = 4 + (HundredDays.getGame().getGameDay() * HundredDays.getGame().getGameDay()) / 4;
        
        lastSpawn += deltaTime;
        if(!HundredDays.getGame().isNight()) return;
        if(mobCount() > MOB_CAP) return;
        
        System.out.println("Can spawn!!");
        System.out.println("Last Spawn: " + lastSpawn + " " + deltaTime);
        
        if(lastSpawn < SPAWN_PERIOD) return;
        
        System.out.println("Spawning !!");
        
        if(random.nextInt(10) == 0) return;
        
        lastSpawn = 0;
        HundredDays.getGame().addGameObject(new Zombie(random.nextInt((int) MapHandler.MAP_SIZE), random.nextInt((int) MapHandler.MAP_SIZE)));
        
        
    }
    
    public int mobCount(){
        int count = 0;
        for(GameObject go : HundredDays.getGame().getGameObjects()){
            if(go instanceof Monster) count++;
        }
        
        return count;
    }
}
