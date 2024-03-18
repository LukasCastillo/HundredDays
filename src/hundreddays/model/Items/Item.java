/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.Items;

/**
 *
 * @author sethl
 */
public class Item {
    private final String name;
    private int count;
    
    public Item(String name){
        this.name = name;
        this.count = 1;
    }
    
    public Item(String name, int count){
        this.name = name;
        this.count = count;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param c the change in count
     */
    public void addCount(int c) {
        this.count += c;
    }
    
    @Override
    public String toString(){
        return this.name + "(" + this.count + ")";
    }
}
