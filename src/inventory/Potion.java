package inventory;

public interface Potion {
    /**
     * Interface for items that affect the hero's counters by a certain value
     * amount
     * Often requires statChange instance variable
     * 
     * @author Chris Dennis
     */
//    private int healthChange;

    public void setPotionValue (int value);


    public int getPotionValue ();

}
