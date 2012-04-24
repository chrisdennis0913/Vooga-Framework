package inventory;

import java.util.ArrayList;
import app.RPGame;

/**
 * Items are constructed in the form 
 * (RPGame, "ItemName, GifName, Category")
 * Be sure to separate by commas and do no use within the Item's Name
 * Subclasses of Item have additional features in the toParse String
 * Any time you need to make an Item, a MakeItems class should
 * be used and passed the game.
 * 
 * This class will no longer be used when the items become jsonable
 * 
 * @author Chris Dennis
 */
public class MakeItems
{
    protected RPGame game;

    public MakeItems (RPGame game2)
    {
        game=game2;
    }


    public Item parseExpression (String input)
    {
        ArrayList<ItemFactory> itemList = new ArrayList<ItemFactory>();
//        itemList.add(Item.getFactory());
        for (ItemFactory itemFact : itemList)
        {
            if (itemFact.isThisKindOfItem(input))
            {
                return itemFact.parseItem(game, input);
            }
        }
        throw new MakeItemsException("Unexpected item type " + input);
    }
}
