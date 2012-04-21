package inventory;

import app.RPGame;

public class BowAndArrows extends Weapon {
    private BowAndArrows(){
        super();
    }
    
    private BowAndArrows (RPGame game2, String name,
                    String gifName,
                   int damage)
    {
        super(game2, name, gifName, damage);
        weaponType="BowAndArrows";
    }
    
    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        return myCateg.equalsIgnoreCase("BowAndArrows");
    }
    
    public static ItemFactory getFactory(){
        return new ItemFactory(new BowAndArrows ());
    }
    @Override
    public Item parseItem (RPGame game2, String toParse)
    {
        return new BowAndArrows(game2, super.parseName(toParse),
                          super.parseGifName(toParse),
                          parseDamage(toParse));
    }


}
