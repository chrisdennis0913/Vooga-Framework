package inventory;

import app.RPGame;


public class Sword extends Weapon {
    /**
     * 
     */
    private static final long serialVersionUID = -3081446463048996331L;


    private Sword () {
        super();
    }


    private Sword (RPGame game2, String name, String gifName, int damage) {
        super(game2, name, gifName, damage);
        weaponType = "sword";
    }


    @Override
    public boolean isThisKindOfItem (String toParse) {
        String myCateg = super.parseCategory(toParse);
        return (myCateg.equalsIgnoreCase("sword"));
    }


    public static ItemFactory getFactory () {
        return new ItemFactory(new Sword());
    }


    @Override
    public Item parseItem (RPGame game2, String toParse) {
        return new Sword(game2,
                         super.parseName(toParse),
                         super.parseGifName(toParse),
                         parseDamage(toParse));
    }
}
