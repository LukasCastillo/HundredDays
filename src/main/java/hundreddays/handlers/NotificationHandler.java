/*
 *  Tau Lukas Castillo
 */
package hundreddays.handlers;

import hundreddays.controllers.GameScreenController;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author sethl
 */
public class NotificationHandler {
    private final Queue<Notification> notifications;
    
    public NotificationHandler(){
        notifications = new LinkedList<>();
    }
    
    public void initialize(GameScreenController controller){
        controller.getNotificationPane().setVisible(false);
        controller.getNotificationPane().setDisable(true);
    }
    
    public void render(GameScreenController controller, double deltaTime){
        Notification currentNotif = notifications.peek();
        if(currentNotif == null){
            controller.getNotificationPane().setVisible(false);
            controller.getNotificationPane().setDisable(true);
            
            return;
        }
        
        controller.getNotificationPane().setVisible(true);
        controller.getNotificationPane().setDisable(false);
        controller.getNotificaitonContent().setText(currentNotif.getMessage());
        controller.getNotificationHeader().setText(currentNotif.getType());
    }
    
    public void addNotificaiton(Notification n){
        this.notifications.add(n);
    }
    
    public void exitNotification(){
        notifications.poll();
    }
}
