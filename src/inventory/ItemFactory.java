package inventory;

import app.RPGame;

public class ItemFactory
{
    private Item myItem;
    
    public ItemFactory(Item item){
        myItem = item;
    }
    public boolean isThisKindOfItem (String toParse){
        return myItem.isThisKindOfItem(toParse);
    }
    public Item parseItem (RPGame game2, String toParse)
    {
        return myItem.parseItem(game2,toParse);
    }
}
