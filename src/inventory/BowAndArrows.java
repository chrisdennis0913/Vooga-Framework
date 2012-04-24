package inventory;

import store.Sellable;
import app.RPGame;

public class BowAndArrows extends Weapon implements Sellable {
    /**
     * Bow Attack style will depend on this 
     * Has category = weapon and
     * weaponType = bowAndArrows
     */
    private static final long serialVersionUID = -9018291996580125601L;
    private BowAndArrows(){
        super();
    }
    
    private int price = 0;
    
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

	@Override
	public void setPrice() {
		price = 500;
		
	}

	@Override
	public int getPrice() {
		return price;
		
	}

	@Override
	public boolean isSellable() {
		if(price == 0)
            return false;
        return true;
	}

	@Override
	public void adjustPrice() {
		// TODO Auto-generated method stub
		
	}


}
