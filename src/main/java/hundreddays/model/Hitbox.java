/*
 *  Tau Lukas Castillo
 */
package hundreddays.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author sethl
 */
public class Hitbox {
    private double centerX, centerY;
    private double width, height;
    private Rectangle rect;
    
    public Hitbox(double centerX, double centerY, double width, double height){
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(0, 0, width, height);
        this.rect.setViewOrder(-10000);
        this.rect.setFill(new Color(1, 0, 0, 0.3));
    }
    
    public Hitbox copy(){
        return new Hitbox(centerX, centerY, width, height);
    }
    
    public boolean intersects(Hitbox h2){
        if((this.width == 0 && this.height == 0) || (h2.width == 0 && h2.height == 0)) return false;
        double p1X = centerX - width / 2;
        double p1Y = centerY - height / 2;
        
        double p2X = h2.centerX - h2.width / 2;
        double p2Y = h2.centerY - h2.height / 2;
        
        return (p1X + this.width > p2X && p1X < p2X + h2.width && p1Y + this.height > p2Y && p1Y < p2Y + h2.height);
    }
    
    public void moveBy(double dX, double dY){
        this.centerX += dX;
        this.centerY += dY;
    }
    
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
    
    public void setPosition(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * @return the centerX
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * @param centerX the centerX to set
     */
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    /**
     * @return the centerY
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * @param centerY the centerY to set
     */
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the rect
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
