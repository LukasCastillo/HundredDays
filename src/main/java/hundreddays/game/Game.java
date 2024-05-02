/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.game;

import hundreddays.controllers.GameScreenController;
import hundreddays.handlers.Camera;
import hundreddays.handlers.DebugHandler;
import hundreddays.handlers.MapHandler;
import hundreddays.handlers.PlayerHandler;
import hundreddays.model.Character;
import hundreddays.model.GameObjects.Entites.Entity;
import hundreddays.model.GameObjects.Entites.Monsters.Zombie;
import hundreddays.model.GameObjects.GameObject;
import hundreddays.model.GameObjects.Tree;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javafx.animation.AnimationTimer;

/**
 *
 * @author TAU
 */
public class Game {
    private PlayerHandler playerHandler;
    
    private MapHandler mapHandler = new MapHandler();
    private Camera camera;
    private DebugHandler debugHandler = new DebugHandler();
    
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> objectsToAdd;
    private ArrayList<GameObject> objectsToDelete;
    
    public static final float GAME_DAY = 120; // in seconds
    private float gameSeconds = 0;
    
    private AnimationTimer gameTimer;
    private double prevUpdateTime = 0;
    public static final double UPDATE_PERIOD = 16.7; // in milliseconds
    private GameScreenController controller;
    private double deltaTime = 0;
    
    public Game(){
        playerHandler = new PlayerHandler(new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0));
        camera = new Camera(0, 0, 400, 600, 400, 600);
        controller = null;
        gameTimer = null;
        
        gameObjects = new ArrayList();
        objectsToAdd = new ArrayList();
        objectsToDelete = new ArrayList();
        
        Random random = new Random();
        for(int i = 0; i < 300; i++){
            addGameObject(new Tree(random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16, random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16, 1));
        }
        
        for(int i = 0; i < 300; i++){
            this.addGameObject(new Zombie(random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16, random.nextInt((int) (MapHandler.MAP_SIZE / 16)) * 16));
        }
    }
    
    public void start(GameScreenController controller){
        System.out.println("Game Started!!!");
        this.controller = controller;
        prevUpdateTime = new Date().getTime();
        
        this.gameTimer = new AnimationTimer(){
            @Override
            public void handle(long l) {
                try{
                    double currentUpdateTime = new Date().getTime();
                    if((currentUpdateTime - prevUpdateTime) < UPDATE_PERIOD) return;
                    
                    setDeltaTime((currentUpdateTime - prevUpdateTime) / 1000);
                    if(getDeltaTime() > 0.5) System.out.println("Lag spike!!");

                    prevUpdateTime = currentUpdateTime;

                    update();
                    render();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
 
        
        gameTimer.start();
        playerHandler.initialize(controller);
        mapHandler.initialize(controller.getBgImage());
        for(GameObject go : gameObjects) go.initialize(controller);
    }
    
    public void update(){
        System.out.println("Updating!");
        
        //update time
        gameSeconds += getDeltaTime();
        
        //update player
        playerHandler.update(getDeltaTime());
        
        //add game objects
        for(GameObject go : this.objectsToAdd){
            this.getGameObjects().add(go);
            go.initialize(controller);
            System.out.println("Added Entity");
            System.out.println(go);
        }
        this.objectsToAdd.clear();
        
        //delete game objects
        for(GameObject go : this.objectsToDelete){
            this.getGameObjects().remove(go);
            go.dispose(controller);
        }
        this.objectsToDelete.clear();
        
        //update entites
        for(GameObject go : this.getGameObjects()){
            if(!(go instanceof Entity)) continue;
                ((Entity) go).update(getDeltaTime());
        }
    }
    
    public void render(){
        if(controller == null) return;
        
        controller.getDayLabel().setText("Day: " + this.getGameDay());
        
        //render map
        mapHandler.renderMap(controller.getBgImage());
        
        //day
        //made funny equation in desmos
        double dayOpacity = 0.5 * Math.max(0, -(10 / (0.625 * Math.pow(GAME_DAY, 2))) * (Math.pow(this.getGameTime() - GAME_DAY / 2, 2) - Math.pow(GAME_DAY / 4, 2)));
        controller.getDayPane().setOpacity(dayOpacity);
        
        //render player
        playerHandler.render(controller, getDeltaTime());
        
        //render objects
        for(GameObject go : this.getGameObjects()){
            go.render(controller);
        } 
        
        System.out.println("Rendering " + this.getGameObjects().size() + " game objects!");
        
        //debug
        long allocatedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        debugHandler.addDebug("Pos", String.format("%.2f %.2f", playerHandler.getPlayer().getXPos(), playerHandler.getPlayer().getYPos()));
        debugHandler.addDebug("FPS", (1 / getDeltaTime()));
        debugHandler.addDebug("Mem", String.format("%dMB/%dMB", allocatedMemory / 1000000, Runtime.getRuntime().maxMemory() / 1000000));
        debugHandler.addDebug("sec", this.getGameSeconds());
        debugHandler.render(controller.getDebugLabel());
    }
    
    public void close(){
        System.out.println("Game Stopped");
        if(gameTimer != null) gameTimer.stop();
        gameTimer = null;
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

    /**
     * @return the camera
     */
    public Camera getCamera() {
        return camera;
    }
    
    public float getGameSeconds(){
        return gameSeconds;
    }
    
    public float getGameTime(){
        return gameSeconds % GAME_DAY;
    }
    
    public int getGameDay(){
        return (int) Math.floor(gameSeconds / GAME_DAY);
    }

    /**
     * @return the debugHandler
     */
    public DebugHandler getDebugHandler() {
        return debugHandler;
    }

    /**
     * @return the deltaTime
     */
    public double getDeltaTime() {
        return deltaTime;
    }

    /**
     * @param deltaTime the deltaTime to set
     */
    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    /**
     * @return the gameObjects
     */
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
