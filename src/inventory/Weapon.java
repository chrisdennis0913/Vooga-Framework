package inventory;

import app.RPGame;


/**
 * Class for items that do damage. Has instance variable myDamage to represent
 * base damage value Weapons are in the form of
 * "Name, weapon, boolean forSale, int Price, int Damage" for example:
 * "Dagger, Weapon, true, 100, 55" or "Sword, Weapon, false, 85"
 * 
 * @author Chris Dennis
 */
public class Weapon extends Item {
    private int myDamage;
    protected String weaponType;


    protected Weapon () {

    }


    protected Weapon (RPGame game2, String name, String gifName, int damage) {
        super(game2, name, gifName);
        myDamage = damage;
        category = "Weapon";
        game2.addItems(this);
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("weapon")) return true;
        return false;
    }


    @Override
    public Item parseItem (RPGame game2, String toParse) {
        return new Weapon(game2,
                          super.parseName(toParse),
                          super.parseGifName(toParse),
                          parseDamage(toParse));
    }


    public int getDamage () {
        return myDamage;
    }


    public void equip () {
        game.getPlayer().getCharacter().getInventory().setEquipped(this);
    }


    public void unequip () {
        if (game.getPlayer().getCharacter().getInventory().getEquipped() == this) game.getPlayer()
                                                        .getCharacter().getInventory().setEquipped(null);
    }


    public boolean isEquipped () {
        return game.getPlayer().getCharacter().getInventory().getEquipped() == this;
    }


    public int parseDamage (String toParse) {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length - 1].trim());

    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new Weapon());
    }


    public String toString () {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(myName + " ");
        result.append("is a weapon which does " + myDamage + " damage");
        result.append(" of type " + weaponType);
        result.append(".)");

        return result.toString();
    }


    public int compareTo (Item it) {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((Weapon) it);

    }


    public int compareTo (Weapon wp) {
        if (myDamage != wp.getDamage()) return myDamage - wp.getDamage();
        if (myName != wp.getName()) return myName.compareTo(wp.getName());
        return 0;
    }


    @Override
    public void use () {
        equip();
    }


    public String getWeaponType () {
        return weaponType;
    }
    public boolean canBeEquipped(){
        return true;
    }


    @Override
    public void drop () {
        // TODO Auto-generated method stub

    }


    @Override
    public void initResources () {
        // TODO Auto-generated method stub
        
    }

}
