/*
 * Make sure the npc safely arrives at the appointed location
 */

package quest;

import gameCharacter.CharacterDecorator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import utils.Location;

public class EscortTask extends Task
{
	private Location loc;
	private CharacterDecorator recipient;

	public EscortTask(String description, CharacterDecorator recipient, Location loc) 
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

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		JsonArray location = new JsonArray();
		location.add(new JsonPrimitive(loc.getX()));
		location.add(new JsonPrimitive(loc.getY()));
		
		json.add("location", location);
		
		return json;
	}
}
