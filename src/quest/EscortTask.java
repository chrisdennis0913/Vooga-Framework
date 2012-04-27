/*
 * Make sure the npc safely arrives at the appointed location
 */

package quest;

import npc.NPC;
import enemy.AbstractEnemy;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import app.RPGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
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
	
	public static class DestroyTaskFactory extends TaskFactory
	{
		public boolean isThisType(String taskName)
		{
			return "Destroy Task".equals(taskName);
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
		
		JsonArray jArray = jTask.getAsJsonArray("location");
		
		Location loc = new Location(new int[] {jArray.get(0).getAsInt(), jArray.get(1).getAsInt() });
		
		if (npc == null)
		{
			throw new RuntimeException("NPC is not available");
		}
			return new EscortTask(jTask.getAsString(), npc, loc);
		}
	}
}
