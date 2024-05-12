/*
 *  Tau Lukas Castillo
 */
package hundreddays.handlers;

import javafx.scene.image.Image;

/**
 *
 * @author sethl
 */
public class Notification {
    private final String type, message;
    private final Image icon;
    
    public Notification(String type, String message){
        this.type = type;
        this.message = message;
        this.icon = null;
    }
    
    public Notification(String type, String message, Image icon){
        this.type = type;
        this.message = message;
        this.icon = icon;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the icon
     */
    public Image getIcon() {
        return icon;
    }
}
