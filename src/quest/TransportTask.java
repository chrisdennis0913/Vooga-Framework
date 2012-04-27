package quest;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import inventory.Inventory;
import inventory.Item;

/*
 * Type of task where the player transports an item from point A to point B
 */

public class TransportTask extends Task
{	
	private Item item;
	private CharacterDecorator recipient;
	private Inventory inv;
	private GameCharacter gC;
	
	public TransportTask(GameCharacter ch, String description, Item item, CharacterDecorator recipient)
	{
		super(description);
		this.item = item;
		this.recipient = recipient;
		gC = ch;
		inv = ch.getInventory();
	}

	public String toString()
	{
		return "transport " + item.toString() + " to " + recipient.toString();
	}

	public boolean checkComplete() 
	{
		isComplete = inv.contains(item);
		return isComplete;
	}

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		json.add("item", new JsonPrimitive(item.getName()));
		
		json.add("character", new JsonPrimitive(gC.getName()));
		
		return json;
	}
}
