/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import app.RPGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;

public class DestroyTask extends Task
{
	private AbstractEnemy recipient;

	public DestroyTask(String description, AbstractEnemy recipient) 
	{
		super(description);
		this.recipient = recipient;
	}

	public boolean checkComplete()
	{
		isComplete = (recipient.getHealth() <= 0);
		return isComplete;
	}
	
	public static class DestroyTaskFactory extends TaskFactory
	{
		public boolean isThisType(String taskName)
		{
			return "Destroy Task".equals(taskName);
		}

		public Task constructTask(RPGame game, GameCharacter gC, JsonObject jTask) 
		{
		SpriteGroup group = game.getField().getGroup("enemies");
		
		AbstractEnemy en = null;
		
		for (Sprite s: group.getSprites())
		{
			if (s instanceof GameCharacter)
			{
				String name = ((GameCharacter) s).getDecorator().getName();
				
				if (name.equals(jTask.getAsJsonObject("enemy")))
				{
					en = (AbstractEnemy) ((GameCharacter) s).getDecorator();
				}
			}
		}
		
		if (en == null)
		{
			throw new RuntimeException("Enemy is not available");
		}
			return new DestroyTask(jTask.getAsString(), en);
		}
	}

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		
		return json;
	}

}
