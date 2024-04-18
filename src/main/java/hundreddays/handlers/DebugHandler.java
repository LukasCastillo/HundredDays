/*
 *  Tau Lukas Castillo
 */
package hundreddays.handlers;

import java.util.HashMap;
import java.util.Map.Entry;
import javafx.scene.control.Label;

/**
 *
 * @author sethl
 */
public class DebugHandler {
    private final HashMap<String, Object> debugMap;
    
    public DebugHandler(){
        debugMap = new HashMap();
    }
    
    public void addDebug(String propery, Object value){
        debugMap.put(propery, value);
    }
    
    public void render(Label debugLabel){
        StringBuilder output = new StringBuilder();
        for(Entry<String, Object> entry : debugMap.entrySet()){
            output = output.append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        System.out.println(output.toString());
        debugLabel.setText(output.toString());
    }

    /**
     * @return the debugMap
     */
    public HashMap<String, Object> getDebugMap() {
        return debugMap;
    }
    
    
}
