/**
 * The classic fetch x amount of this_item and bring it to so and so
 * A HashMap is used in the constructor in case items of multiple types are required
 */

package quest;

import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import inventory.Inventory;
import inventory.Item;

public class FetchTask extends Task 
{

	private HashMap<Item,Integer> itemsToFetch;
	private CharacterDecorator recipient;
	private Inventory inv;
	private GameCharacter gC;
	
	public FetchTask(GameCharacter ch, String description, CharacterDecorator recipient, HashMap<Item, Integer> itemsToFetch) 
	{
		super(description);
		this.itemsToFetch = itemsToFetch;
		this.recipient = recipient;
		gC = ch;
		inv = ch.getInventory();
	}
	

	public void update()
	{
		isComplete = true;
		for (Item s: itemsToFetch.keySet())
		{
			if (inv.getCount(s) != itemsToFetch.get(s))
				isComplete = false;
		}
	}

	public String toString()
	{
		String str = "Collect";
		for (Item s: itemsToFetch.keySet())
		{
			Integer amount = itemsToFetch.get(s);
			str += amount + " " + s.toString(); 
			if (amount > 1)
			{
				str += "s";
			}
		}
		str += " for" + recipient.toString();
		return str;
	}
	
	public boolean checkComplete() 
	{
		isComplete = true;
		for (Item s: itemsToFetch.keySet())
		{
			if (inv.getCount(s) != itemsToFetch.get(s))
				isComplete = false;
		}
		return isComplete;
	}

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		json.add("character", new JsonPrimitive(gC.getName()));
		
		return json;
	}
}
