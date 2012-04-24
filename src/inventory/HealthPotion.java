package inventory;

import java.awt.Graphics2D;

import store.Sellable;
import app.RPGame;

public class HealthPotion extends Item implements Sellable {
    /**
     * Class for items that affect the hero's counters. Health potions are of the
     * form "Name, gif name, healthpotion, value of change" for example
     * "Super Potion, potion, healthpotion, 4"
     * 
     * @author Chris Dennis
     */
    private static final long serialVersionUID = 2489280342490018746L;
    private int healthChange;
    private int price = 0;

    // do I need to change the wrapper when added to a different inventory?
    // yes, yes i do. and will do
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
        wrapper.getCharacter().getCounters().get("health").decrease(1); // might break
        wrapper.getCharacter()
               .getCounters()
               .get("health")
               .increase(healthChange + 1);
        remove(1);
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
        if (wrapper.getCharacter().getInventory().getEquipped() == this) game.getPlayer()
                                                                             .getCharacter()
                                                                             .getInventory()
                                                                             .setEquipped(null);
    }


    public boolean isEquipped () {
        return wrapper.getCharacter().getInventory().getEquipped() == this;
    }


    public boolean canBeEquipped () {
        return false;
    }


    @Override
    public void drop () {
        // TODO Auto-generated method stub   
    }



	@Override
	public void setPrice() {
		price = 100;
		
	}


	@Override
	public int getPrice() {
		return price;
	}


	@Override
	public void adjustPrice() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isSellable() {
		if(price == 0)
            return false;
        return true;
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
