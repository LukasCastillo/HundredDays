/*
 *  Tau Lukas Castillo
 */
package hundreddays.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A hitbox represents a 2d area where the player cant pass though composed of a x and y center position and the width and height of the hitbox.
 * Note that hitboxes uses center position instead of corner position.
 * @author Seth Lukas C. Castillo
 */
public class Hitbox {
    private double centerX, centerY;
    private double width, height;
    private Rectangle rect;
    
    /**
    Creates a new Hitbox with a specified centerX, centerX, width and height. 
     * @param centerX x center position of hitbox
     * @param height height of the hitbox
     * @param centerY y center position of hitbox
     * @param width width of the hitbox
    */
    public Hitbox(double centerX, double centerY, double width, double height){
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(0, 0, width, height);
        this.rect.setViewOrder(-10000);
        this.rect.setFill(new Color(1, 0, 0, 0.3));
    }
    
    /**
    Creates a new instance of a hitbox with the same parameters (position, dimensions) of this hitbox.
     * @return The new instantiated hitbox
    */
    public Hitbox copy(){
        return new Hitbox(centerX, centerY, width, height);
    }
    
    /**
        Checks if this hitbox intersects with a specified hitbox.

        @param h2 The hitbox to check for intersection

        @return Whether the specified and this hitbox intersect.
    */
    public boolean intersects(Hitbox h2){
        if((this.width == 0 && this.height == 0) || (h2.width == 0 && h2.height == 0)) return false;
        double p1X = centerX - width / 2;
        double p1Y = centerY - height / 2;
        
        double p2X = h2.centerX - h2.width / 2;
        double p2Y = h2.centerY - h2.height / 2;
        
        return (p1X + this.width > p2X && p1X < p2X + h2.width && p1Y + this.height > p2Y && p1Y < p2Y + h2.height);
    }
    
    /**
        Changes the position of the hitbox by the specified x and y offsets.
        
        @param dX amount to move hitbox in the x direction
        @param dY amount to move hitbox in the y direction
    */
    public void moveBy(double dX, double dY){
        this.centerX += dX;
        this.centerY += dY;
    }
    
    /**
        Moves this hitbox out from the specified hitbox with the shortest path. This method is used to push out this hitbox when it intersects with the given hitbox.
    
        @param border The hitbox that is treated as immovable.
    */
    public void pushOut(Hitbox border){
        assert this.intersects(border);
        
        double top = (border.centerY + border.height / 2) - (this.centerY - this.height / 2);
        double bottom = (this.centerY + this.height / 2) - (border.centerY - border.height / 2);
        double right = (border.centerX + border.width / 2) - (this.centerX - this.width / 2);
        double left = (this.centerX + this.width / 2) - (border.centerX - border.width / 2);
        
        double minPush = min(top, bottom, right, left);
        
        if(minPush == top) this.setCenterY(border.centerY + border.height / 2 + this.height / 2);
        else if(minPush == bottom) this.setCenterY(border.centerY - border.height / 2 - this.height / 2);
        else if(minPush == right) this.setCenterX(border.centerX + border.width / 2 + this.width / 2);
        else if(minPush == left) this.setCenterX(border.centerX - border.width / 2 - this.width / 2);
    }
    
    /**
        Sets the position of the hitbox to the specified x and y center positions.
    
        @param centerX New x center position of hitbox.
        @param centerY New y center position of hitbox.
    */
    public void setPosition(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * Gets the center x position of the Hitbox.
     * 
     * @return the centerX
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * Sets the center x position of the Hitbox.
     * 
     * @param centerX the centerX to set
     */
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    /**
     * Gets the center y position of the Hitbox.
     * 
     * @return the centerY
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Sets the center y position of the Hitbox.
     * 
     * @param centerY the centerY to set
     */
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    /**
     * Gets the width of the Hitbox.
     * 
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the Hitbox.
     * 
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the height of the Hitbox
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the height of the Hitbox
     * 
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the Drawable rectangle with the same dimensions and postion as the hitbox.
     * 
     * @return The Rectangle Node
     */
    public Rectangle getRect() {
        return rect;
    }
    
    private double min(double ...values){
        assert values.length > 0;
        double mval = values[0];
        
        for(int i = 0; i < values.length; i++){
            if(values[i] < mval) mval = values[i];
        }
        
        return mval;
    }
}
