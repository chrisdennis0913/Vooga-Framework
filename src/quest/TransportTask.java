package quest;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import inventory.ConcreteItem;
import inventory.Inventory;
import inventory.Item;
import npc.NPC;
import app.RPGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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
	
	public static class DestroyTaskFactory extends TaskFactory
	{
		public boolean isThisType(String taskName)
		{
			return "Transport Task".equals(taskName);
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
		
		if (npc == null)
		{
			throw new RuntimeException("NPC is not available");
		}
			return new TransportTask(gC, jTask.getAsString(), new ConcreteItem(game, jTask.getAsJsonObject("item")) ,npc);
		}
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
