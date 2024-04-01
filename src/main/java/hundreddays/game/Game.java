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
import hundreddays.model.GameObjects.Entites.Entity;
import hundreddays.model.GameObjects.GameObject;
import hundreddays.model.GameObjects.Tree;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
    
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> objectsToAdd;
    private ArrayList<GameObject> objectsToDelete;
    
    ScheduledService<Double> gameService;
    private double prevUpdateTime = 0;
    public static final int UPDATE_PERIOD = 50;
    private GameScreenController controller;
    private double deltaTime = 0;
    
    public Game(){
        playerHandler = new PlayerHandler(new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0));
        controller = null;
        gameService = null;
        
        gameObjects = new ArrayList();
        objectsToAdd = new ArrayList();
        objectsToDelete = new ArrayList();
        
        for(int i = 0; i < 100; i++){
            Random random = new Random();
            addGameObject(new Tree(random.nextInt(1000), random.nextInt(1000), 1));
        }
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
                        return 0.0;
                    }
                };
                
                task.setOnSucceeded(workerStateEvent -> {
                    update();
                    render();
                });
                
                return task;
            }
            
        };
        
        gameService.setPeriod(Duration.millis(UPDATE_PERIOD));
        gameService.start();
    }
    
    public void update(){
        System.out.println("Updating!");
        System.out.println("Game Objects:");
        System.out.println(gameObjects);
        double currentUpdateTime = new Date().getTime();
        deltaTime = (currentUpdateTime - prevUpdateTime) / 1000;
        prevUpdateTime = currentUpdateTime;
        
        //update player
        playerHandler.update(deltaTime);
        
        //add game objects
        for(GameObject go : this.objectsToAdd){
            this.gameObjects.add(go);
            go.initialize(controller);
            System.out.println("Added Entity");
            System.out.println(go);
        }
        this.objectsToAdd.clear();
        
        //delete game objects
        for(GameObject go : this.objectsToDelete){
            this.gameObjects.remove(go);
            go.dispose(controller);
        }
        this.objectsToDelete.clear();
        
        //update entites
        System.out.println(gameObjects);
        for(GameObject go : this.gameObjects){
            if(!(go instanceof Entity)) continue;
            ((Entity) go).update(deltaTime);
        }
    }
    
    public void render(){
        if(controller == null) return;
        
        //render map
        mapHandler.renderMap(controller.getBgImage());
        
        //render player
        
        //render objects
        for(GameObject go : this.gameObjects){
            go.render(controller);
            System.out.println(go);
        } 
        
        controller.getFpsLabel().setText("FPS: " + (1 / deltaTime));
    }
    
    public void close(){
        System.out.println("Game Stopped");
        gameService.cancel();
        controller = null;
    }
    
    public PlayerHandler getPlayerHandler(){
        return playerHandler;
    }
    
    public void addGameObject(GameObject go){
        this.objectsToAdd.add(go);
    }
    
    public void deleteGameObject(GameObject go){
        this.objectsToDelete.add(go);
    }
}
