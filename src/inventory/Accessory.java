package inventory;

import java.awt.Graphics2D;
import app.RPGame;


/**
 * Class for items that attach to the hero and contribute to his stats.
 * Accessories are of the form
 * "Name, gifName, accessory, statToChange, valueOfChange" for example
 * "Charismatic Chainmail, chainmail, accessory, health, 8"
 * 
 * @author Chris Dennis
 */
public class Accessory extends Item {
    private boolean showsOnScreen = true;
    private int statChange;
    private int relX;
    private int relY;
    private String statisticalCategory;


    private Accessory () {

    }


    public Accessory (RPGame game2,
                      String name,
                      String gifName,
                      String stat,
                      int value,
                      int relativeX,
                      int relativeY) {
        super(game2, name, gifName);
        category = "Accessory";
        game2.addItems(this);
        statisticalCategory = stat;
        statChange = value;
        relX = relativeX;
        relY = relativeY;
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        return myCateg.equalsIgnoreCase("Accessory");
    }


    @Override
    public Item parseItem (RPGame game2, String toParse) {
        return new Accessory(game2,
                             super.parseName(toParse),
                             super.parseGifName(toParse),
                             parseStatCategory(toParse),
                             parseStatValue(toParse),
                             parseXValue(toParse),
                             parseYValue(toParse));
    }


    public String parseStatCategory (String toParse) {
        String[] parseArray = toParse.split(",");
        return parseArray[2].trim();
    }


    private int parseStatValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 3].trim());
    }


    private int parseXValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 2].trim());
    }


    private int parseYValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 1].trim());
    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new Accessory());
    }
    
    public boolean canBeEquipped(){
        return false;
    }


    @Override
    public void use () {}


    public int getStatChange () {
        return statChange;
    }


    public int compareTo (Item it) {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((Accessory) it);
    }


    public int compareTo (Accessory ac) {
        if (statisticalCategory != ac.getStatistic()) return statisticalCategory.compareTo(ac.getStatistic());
        if (statChange != ac.getStatChange()) return statChange -
                                                     ac.getStatChange();
        if (myName != ac.getName()) return myName.compareTo(ac.getName());
        return 0;
    }


    public String getStatistic () {
        return statisticalCategory;
    }


    public void render (Graphics2D g) {
        if (!showsOnScreen) return;
        mySprite.setActive(true);
        mySprite.setLayer(10);
        mySprite.setLocation(game.getPlayer().getCharacter().getX() + relX,
                             game.getPlayer().getCharacter().getY() + relY);
        mySprite.render(g);
    }


    public void equip () {
        wrapper.getCharacter().getInventory().equipAcc(this);
        wrapper.getCharacter().getCounters().get(statisticalCategory).boostTotal(statChange);
        wrapper.getCharacter().getCounters().get(statisticalCategory).decrease(1);
        wrapper.getCharacter().getCounters().get(statisticalCategory).increase(statChange + 1);
    }


    public void unequip () {
        wrapper.getCharacter().getInventory().unEquipAcc(this);
        wrapper.getCharacter().getCounters().get(statisticalCategory).boostTotal(-statChange);
        wrapper.getCharacter().getCounters().get("health").decrease(statChange);
    }


    public boolean isEquipped () {
        return wrapper.getCharacter().getInventory().isEquipped(this);
    }


    @Override
    public void drop () {
        // TODO Auto-generated method stub   
    }


    @Override
    public void initResources () {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void update (long elapsed) {
        // TODO Auto-generated method stub
        
    }
}
