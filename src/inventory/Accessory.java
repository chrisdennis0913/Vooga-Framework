package inventory;

public interface Accessory {
    /**
     * Interface for items that attach to the hero and contribute to his stats.
     * Accessories require variables such as
     * Name, positionRelativeToPlayer, statToChange, valueOfChange
     * 
     * @author Chris Dennis
     */

    void setStatCategory (String statistic);


    void setStatChange (int value);


    String getStatCategory ();


    int getStatChange ();


    void setRelPosition (int x, int y);


    int[] getRelPosition ();
}
