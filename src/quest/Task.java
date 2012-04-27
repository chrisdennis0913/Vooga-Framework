package quest;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class Task 
{
	protected boolean isComplete =  false;
	protected String description;
	
	public Task (String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return toString();
	}
	
	public JsonObject toJson()
	{
		JsonObject json = getJsonAttributes();
		json.add("name", new JsonPrimitive(description));
		json.add("isComplete", new JsonPrimitive(isComplete));
		
		return null;
	}
	
	public abstract boolean checkComplete();
	
	/**
	 * Get attributes of implementation-specific subclass
	 * of the task
	 * @return JsonObject with subclass-specific attributes
	 */
	public abstract JsonObject getJsonAttributes();
}
