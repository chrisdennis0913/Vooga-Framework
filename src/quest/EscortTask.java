/*
 * Make sure the npc safely arrives at the appointed location
 */

package quest;

import utils.Location;
import npc.NPC;

public class EscortTask extends Task
{
	private Location loc;
	private NPC recipient;

	public EscortTask(String description, NPC recipient, Location loc) 
	{
		super(description);
		this.loc = loc;
		this.recipient = recipient;
	}
	
	public String toString()
	{
		return "Safely escort " + recipient.toString();
	}

	public boolean checkComplete() {
		isComplete = (loc == recipient.getCharacter().getLocation() );
		return isComplete;
	}

}
