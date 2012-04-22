package inventory;

import java.awt.Graphics2D;
import app.RPGame;


/**
 * Class for items that affect the hero's counters. Health potions are of the
 * form "Name, gif name, healthpotion, value of change" for example
 * "Super Potion, potion, healthpotion, 4"
 * 
 * @author Chris Dennis
 */
public class HealthPotion extends Item {
    private int healthChange;


    private HealthPotion () {

    }


    public HealthPotion (RPGame game2, String name, String gifName, int value) {
        super(game2, name, gifName);
        category = "HealthPotion";
        game2.addItems(this);
        healthChange = value;
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        return myCateg.equalsIgnoreCase("HealthPotion");
    }


    @Override
    public Item parseItem (RPGame game2, String toParse) {
        return new HealthPotion(game2,
                                super.parseName(toParse),
                                super.parseGifName(toParse),
                                parsePotionValue(toParse));
    }


    private int parsePotionValue (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 1].trim());
    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new HealthPotion());
    }


    @Override
    public void use () {
        game.getPlayer().getPCs().getHealth().decrease(1);
        game.getPlayer().getPCs().getHealth().increase(healthChange + 1);
    }


    public int getHealthChange () {
        return healthChange;
    }


    public int compareTo (Item it) {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((HealthPotion) it);
    }


    public int compareTo (HealthPotion hp) {
        if (healthChange != hp.getHealthChange()) return healthChange -
                                                         hp.getHealthChange();
        if (myName != hp.getName()) return myName.compareTo(hp.getName());
        return 0;
    }


    public void equip () {}


    public void unequip () {
        if (game.getPlayer().getEquipped() == this) game.getPlayer()
                                                        .setEquipped(null);
    }


    public boolean isEquipped () {
        return game.getPlayer().getEquipped() == this;
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
    public void render (Graphics2D g) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void update (long elapsed) {
        // TODO Auto-generated method stub
        
    }
}
