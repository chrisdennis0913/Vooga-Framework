package inventory;

public interface Accessory {
    /**
     * Interface for items that attach to the hero and contribute to his stats.
     * Accessories are of the form
     * "Name, gifName, accessory, statToChange, valueOfChange" for example
     * "Charismatic Chainmail, chainmail, accessory, health, 8"
     * 
     * @author Chris Dennis
     */
//    private boolean showsOnScreen = true;
//    private int statChange;
//    private int relX;
//    private int relY;
//    private String statisticalCategory;

    void setStatCategory (String statistic);


    void setStatChange (int value);


    String getStatCategory ();


    int getStatChange ();


    void setRelPosition (int x, int y);


    int[] getRelPosition ();
}
