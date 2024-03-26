/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.game;

import hundreddays.HundredDays;
import hundreddays.controllers.GameScreenController;
import hundreddays.handlers.MapHandler;
import hundreddays.handlers.PlayerHandler;
import hundreddays.model.Character;
import hundreddays.model.GameObjects.GameObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

/**
 *
 * @author TAU
 */
public class Game {
    private PlayerHandler playerHandler;
    
    private MapHandler mapHandler = new MapHandler();
    
    ScheduledService<Double> gameService;
    private double prevUpdateTime = 0;
    public static final int UPDATE_PERIOD = 50;
    private GameScreenController controller;
    private double deltaTime = 0;
    
    public Game(){
//        player = new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0);
        playerHandler = new PlayerHandler(new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0));
        controller = null;
        gameService = null;
    }
    
    public void start(GameScreenController controller){
        System.out.println("Game Started!!!");
        this.controller = controller;
        prevUpdateTime = new Date().getTime();
        
        this.gameService = new ScheduledService<>(){
            @Override
            protected Task<Double> createTask() {
                Task task = new Task<Double>(){
                    @Override
                    protected Double call() throws Exception {
                        update();
                        return 0.0;
                    }
                    
                };
                
                task.setOnSucceeded(workerStateEvent -> render());
                
                return task;
            }
            
        };
        
        gameService.setPeriod(Duration.millis(UPDATE_PERIOD));
        gameService.start();
    }
    
    public void update(){
        System.out.println("Updating!");
        double currentUpdateTime = new Date().getTime();
        deltaTime = (currentUpdateTime - prevUpdateTime) / 1000;
        prevUpdateTime = currentUpdateTime;
        
        playerHandler.update(deltaTime);
    }
    
    public void render(){
        if(controller == null) return;
        
        mapHandler.renderMap(controller.getBgImage());
        
        controller.getFpsLabel().setText("FPS: " + (1 / deltaTime));
    }
    
    public void close(){
        System.out.println("Game Stopped");
//        timer.cancel();
        gameService.cancel();
        controller = null;
    }
    
    public PlayerHandler getPlayerHandler(){
        return playerHandler;
    }
}
