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
    private double zoom;
    
    public Camera(double centerX, double centerY, double viewH, double viewW, double worldH, double worldW){
        this.centerX = centerX;
        this.centerY = centerY;
        this.viewW = viewW;
        this.viewH = viewH;
        this.worldH = worldH;
        this.worldW = worldW;
        this.zoom = 1;
    }
    
    
    /**
     * Changes scale and position of the image to match the perspective of the camera
     * 
     * @param view ImageView to update
     * @param imageX center X position
     * @param imageY center Y position
     */
    public void renderImage(ImageView view, double imageX, double imageY){
        view.scaleXProperty().set(viewW / worldW * zoom);
        view.scaleYProperty().set(viewH / worldH * zoom);
        
        double imgW = view.boundsInParentProperty().get().getWidth() / view.scaleXProperty().doubleValue();
        double imgH = view.boundsInParentProperty().get().getHeight()/ view.scaleYProperty().doubleValue();
        
        view.setX(viewW / 2 - view.boundsInParentProperty().get().getWidth() / 2);
        view.setY(viewH / 2 - view.boundsInParentProperty().get().getHeight()/ 2);
        
        double offsetX = imgW / 2 - imgW * (viewW / worldW * zoom) / 2;
        double offsetY = imgH / 2 - imgH * (viewH / worldH * zoom) / 2;

//        System.out.println("Camera:");
//        System.out.println("Dimen: " + imgW + " " + imgH);
//        System.out.println(imageX + " " + imageY + " " + offsetX + " " + offsetY);
//        System.out.println(imgW + " " + imgH);
//        System.out.println("Zoom: " + zoom);
//        System.out.println(viewW + " " + viewH + " | " + worldW + " " + worldH  );
        
        view.translateXProperty().set((imageX - centerX) * (viewW / worldW * zoom) - offsetX);
        view.translateYProperty().set((imageY - centerY) * (viewH / worldH * zoom) - offsetY);
        
//        System.out.println("pos: " + view.getX() + " " + view.getY());
//        System.out.println("scale: " + view.scaleXProperty().get() + " " + view.scaleYProperty().get());
//        System.out.println("translate: " + view.translateXProperty().get() + " " + view.translateYProperty().get());
    }
    
    public void zoom(double z){
//        double zoomZ = viewW / worldW;
//        double zoomY = viewH / worldH;
//        this.worldW = viewW / (z + zoomZ);
//        this.worldH = viewH / (z + zoomY);
        
        zoom += z;
    }
    
    public void setZoom(double z){
//        this.worldW = viewW / z;
//        this.worldH = viewH / z;
        zoom = z;
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
