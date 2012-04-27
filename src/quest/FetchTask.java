/**
 * The classic fetch x amount of this_item and bring it to so and so
 * A HashMap is used in the constructor in case items of multiple types are required
 */

package quest;

import java.util.HashMap;

import npc.NPC;
import utils.Location;

import app.RPGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import inventory.ConcreteItem;
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
	
	public static class FetchTaskFactory extends TaskFactory
	{
		public boolean isThisType(String taskName)
		{
			return "Fetch Task".equals(taskName);
		}

		public Task constructTask(RPGame game, GameCharacter gC, JsonObject jTask) 
		{
		SpriteGroup group = game.getField().getGroup("npcs");
		
		NPC npc = null;
		
		for (Sprite s: group.getSprites())
		{
			if (s instanceof GameCharacter)
			{
				String name = ((GameCharacter) s).getDecorator().getName();
				
				if (name.equals(jTask.getAsJsonObject("recipient")))
				{
					npc = (NPC) ((GameCharacter) s).getDecorator();
				}
			}
		}
		
		JsonArray jArray1 = jTask.getAsJsonArray("array1");
		JsonArray jArray2 = jTask.getAsJsonArray("array2");
		
		HashMap<Item, Integer> items = new HashMap<Item, Integer>();
		
		for (int i = 0; i < jArray1.size(); i++)
		{
			items.put(new ConcreteItem(game, jArray1.get(i).getAsJsonObject()), jArray2.get(i).getAsInt());
		}
		
		if (npc == null)
		{
			throw new RuntimeException("NPC is not available");
		}
			return new FetchTask(gC, jTask.getAsString(), npc, items);
		}
	}
	
	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		json.add("character", new JsonPrimitive(gC.getName()));
		
		JsonArray jArray1 = new JsonArray();
		JsonArray jArray2 = new JsonArray();
		
		for (Item it: itemsToFetch.keySet())
		{
			jArray1.add(new JsonPrimitive(it.getName()));
			jArray2.add(new JsonPrimitive(itemsToFetch.get(it)));
		}
		
		json.add("array1", jArray1);
		json.add("array2", jArray2);
		
		return json;
	}
}
