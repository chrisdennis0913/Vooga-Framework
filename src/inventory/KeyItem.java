package inventory;

import app.RPGame;


/**
 * Class for items that must be collected to complete the game. KeyItems are of
 * the form "Name, keyitem, boolean forSale, int Price" for example
 * "Master Key, keyitem, false"
 * 
 * @author Chris Dennis
 */
public class KeyItem extends Item {
    /**
     * 
     */
    private static final long serialVersionUID = -7099515374848295302L;
//    private int price = 45;

    private KeyItem () {

    }


    public KeyItem (RPGame game2, String name, String gifName) {
        super(game2, name, gifName);
        category = "KeyItem";
        game2.addItems(this);
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("keyitem")) return true;
        return false;
    }


    @Override
    public Item parseItem (RPGame game2, String toParse) {
        return new KeyItem(game2,
                           super.parseName(toParse),
                           super.parseGifName(toParse));
    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new KeyItem());
    }
    
    public boolean canBeEquipped(){
        return false;
    }

    @Override
    public void use () {
        // TODO Auto-generated method stub

    }


    @Override
    public void equip () {
        // TODO Auto-generated method stub

    }


    @Override
    public void unequip () {
        // TODO Auto-generated method stub

    }


    @Override
    public void drop () {
        System.out.println("Cannot drop key items");

    }


    @Override
    public boolean isEquipped () {
        return false;
    }


    @Override
    public void initResources () {
        // TODO Auto-generated method stub
        
    }
}
