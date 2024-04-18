/*
 *  Tau Lukas Castillo
 */
package hundreddays.handlers;

import hundreddays.HundredDays;
import javafx.scene.image.ImageView;

/**
 *
 * @author sethl
 */
public class Camera {
    private double centerX, centerY;
    private double viewH, viewW;
    private double worldH, worldW;
    
    public Camera(double centerX, double centerY, double viewH, double viewW, double worldH, double worldW){
        this.centerX = centerX;
        this.centerY = centerY;
        this.viewW = viewW;
        this.viewH = viewH;
        this.worldH = worldH;
        this.worldW = worldW;
    }
    
    public void renderImage(ImageView view, double imageX, double imageY){
        
        double aspectRatio = view.getImage().getWidth() / view.getImage().getHeight();
        double imgW = Math.min(view.getFitWidth(), view.getFitHeight() * aspectRatio);
        double imgH = Math.min(view.getFitHeight(), view.getFitWidth() / aspectRatio);
        
        view.setX(viewW / 2);
        view.setY(viewH / 2);
        
        view.scaleXProperty().set(viewW / worldW);
        view.scaleYProperty().set(viewH / worldH);
        
        double offsetX = imgW / 2 - imgW * (viewW / worldW) / 2;
        double offsetY = imgH / 2 - imgH * (viewH / worldH) / 2;
        
        double playX = -HundredDays.getGame().getPlayerHandler().getPlayer().getXPos();
        double playY = HundredDays.getGame().getPlayerHandler().getPlayer().getYPos();
        
//        System.out.println("Camera:");
//        System.out.println(imageX + " " + imageY + " " + offsetX + " " + offsetY);
//        System.out.println(imgW + " " + imgH);
//        System.out.println("Zoom: " + viewW / worldW + "  " + viewH / worldH);
        
        view.translateXProperty().set((imageX + playX) * (viewW / worldW) - offsetX);
        view.translateYProperty().set((imageY + playY) * (viewH / worldH) - offsetY);
        
//        System.out.println("pos: " + viewW / 2 + " " + viewH / 2);
//        System.out.println("scale: " + (viewW / worldW) + " " + viewH / worldH);
//        System.out.println("translate: " + (imageX - offsetX - HundredDays.getGame().getPlayerHandler().getPlayer().getXPos()) + " " + (imageY - offsetY + HundredDays.getGame().getPlayerHandler().getPlayer().getYPos()));
    }
    
    public void zoom(double z){
        double zoomZ = viewW / worldW;
        double zoomY = viewH / worldH;
        this.worldW = viewW / (z + zoomZ);
        this.worldH = viewH / (z + zoomY);
    }
    
    public void setZoom(double z){
        this.worldW = viewW / z;
        this.worldH = viewH / z;
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
     * @return the viewH
     */
    public double getViewH() {
        return viewH;
    }

    /**
     * @param viewH the viewH to set
     */
    public void setViewH(double viewH) {
        this.viewH = viewH;
    }

    /**
     * @return the viewW
     */
    public double getViewW() {
        return viewW;
    }

    /**
     * @param viewW the viewW to set
     */
    public void setViewW(double viewW) {
        this.viewW = viewW;
    }

    /**
     * @return the worldH
     */
    public double getWorldH() {
        return worldH;
    }

    /**
     * @param worldH the worldH to set
     */
    public void setWorldH(double worldH) {
        this.worldH = worldH;
    }

    /**
     * @return the worldW
     */
    public double getWorldW() {
        return worldW;
    }

    /**
     * @param worldW the worldW to set
     */
    public void setWorldW(double worldW) {
        this.worldW = worldW;
    }
}
