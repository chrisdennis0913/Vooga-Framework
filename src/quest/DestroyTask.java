/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import enemy.AbstractEnemy;

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

	public JsonObject getJsonAttributes() 
	{
		JsonObject json = new JsonObject();
		json.add("recipient", new JsonPrimitive(recipient.getName()));
		
		return json;
	}

}
