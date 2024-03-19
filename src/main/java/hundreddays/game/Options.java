/*
 *  Tau Lukas Castillo
 */
package hundreddays.game;

import hundreddays.enums.KeyAction;
import hundreddays.exceptions.KeybindAlreadyExisitsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javafx.scene.input.KeyCode;

/**
 *
 * @author sethl
 */
public class Options {
    private double soundLevel;
    private boolean fullScreen;
    private Map<KeyAction, KeyCode> keyBinds;
    
    public Options(){
        this.soundLevel = 100;
        this.fullScreen = false;
        this.keyBinds = new HashMap<>();
        
        //default keybinds
        this.keyBinds.put(KeyAction.MOVE_UP, KeyCode.W);
        this.keyBinds.put(KeyAction.MOVE_DOWN, KeyCode.S);
        this.keyBinds.put(KeyAction.MOVE_LEFT, KeyCode.A);
        this.keyBinds.put(KeyAction.MOVE_RIGHT, KeyCode.D);
    }

    /**
     * @return the soundLevel
     */
    public double getSoundLevel() {
        return soundLevel;
    }

    /**
     * @param soundLevel the soundLevel to set
     */
    public void setSoundLevel(double soundLevel) {
        this.soundLevel = soundLevel;
    }

    /**
     * @return the fullScreen
     */
    public boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * @param fullScreen the fullScreen to set
     */
    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
    
    public KeyCode getKeyBind(KeyAction ka){
        return this.keyBinds.get(ka);
    }
    
    public KeyCode setKeyBind(KeyAction ka, KeyCode code) throws KeybindAlreadyExisitsException{
        KeyAction prevAction = getAssociatedKeyAction(code);
        if(prevAction != null && prevAction != ka) 
            throw new KeybindAlreadyExisitsException("KeyBind with: " + code + " already set!");
        return this.keyBinds.put(ka, code);
    }
    
    public KeyAction getAssociatedKeyAction(KeyCode c){
        //wtf
        List<KeyAction> keyList = this.keyBinds.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), c))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        if(keyList.isEmpty()) return null;
        else return keyList.get(0);
    }
}
