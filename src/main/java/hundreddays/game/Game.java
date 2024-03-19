/*
 *  Seth Lukas Castillo
 *  Tau
 */
package hundreddays.game;

import hundreddays.model.Character;
import hundreddays.model.GameObjects.GameObject;
import java.util.ArrayList;

/**
 *
 * @author TAU
 */
public class Game {
    public Character player;
    
    public Game(){
        player = new Character("Lukas", 100, 100, 20, 10, "Swordsman", 0, 0);
    }
}
