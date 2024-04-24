/*
 * Seth Lukas Castillo
 * Tau
 */
package hundreddays.model.GameObjects.Entites;

import hundreddays.model.GameObjects.GameObject;


/**
 *
 * @author TAU
 */
public abstract class Entity extends GameObject{
    protected double speed;
    protected double hp;
    
    public Entity(double x, double y, String texture, double speed, int hp) {
        super(x, y, texture);
        this.speed = speed;
        this.hp = hp;
    }
    
    public abstract void update(double deltaTime);

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return the hp
     */
    public double getHp() {
        return hp;
    }
    
    public void moveBy(double dX, double dY){
        this.xPos += dX;
        this.yPos += dY;
    }
    
    public void setPosition(double x, double y){
        this.xPos = x;
        this.yPos = y;
    }
}
