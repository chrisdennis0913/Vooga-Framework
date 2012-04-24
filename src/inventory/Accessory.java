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

//    public void render (Graphics2D g) {
//        if (!showsOnScreen) return;
//        mySprite.setActive(true);
//        mySprite.setLayer(10);
//        mySprite.setLocation(game.getPlayer().getCharacter().getX() + relX,
//                             game.getPlayer().getCharacter().getY() + relY);
//        mySprite.render(g);
//    }
//
//
//    public void equip () {
//        wrapper.getCharacter().getInventory().equipAcc(this);
//        wrapper.getCharacter().getCounters().get(statisticalCategory).boostTotal(statChange);
//        wrapper.getCharacter().getCounters().get(statisticalCategory).decrease(1);
//        wrapper.getCharacter().getCounters().get(statisticalCategory).increase(statChange + 1);
//    }
//
//
//    public void unequip () {
//        wrapper.getCharacter().getInventory().unEquipAcc(this);
//        wrapper.getCharacter().getCounters().get(statisticalCategory).boostTotal(-statChange);
//        wrapper.getCharacter().getCounters().get("health").decrease(statChange);
//    }
//
//
//    public boolean isEquipped () {
//        return wrapper.getCharacter().getInventory().isEquipped(this);
//    }
}
