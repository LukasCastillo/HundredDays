/*
 *  Tau Lukas Castillo
 */
package hundreddays.model.Items;

/**
 *
 * @author sethl
 */
public class Food extends Item{
    private double foodPoints;
    
    public Food(String name, int count, int foodPoints) {
        super(name, count);
        this.foodPoints = foodPoints;
    }

    /**
     * @return the foodPoints
     */
    public double getFoodPoints() {
        return foodPoints;
    }
    
}
