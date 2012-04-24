package quest;

import app.RPGame;
import gameCharacter.GameCharacter;
import inventory.Inventory;
import inventory.Item;
import npc.NPC;

/*
 * Type of task where the player transports an item from point A to point B
 */

public class TransportTask extends Task
{
	
	private Item item;
	private NPC recipient;
	private Inventory inv;
	
	public TransportTask(GameCharacter ch, String description, Item item, NPC recipient)
	{
		super(description);
		this.item = item;
		this.recipient = recipient;
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

}
