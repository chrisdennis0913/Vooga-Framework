package quest;

import gameCharacter.GameCharacter;

import java.util.ArrayList;

import app.RPGame;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class Task 
{
	protected boolean isComplete =  false;
	protected String description;
	private static ArrayList<TaskFactory> taskFactories = new ArrayList<TaskFactory>();
	
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
	
	public static Task createTask(String taskType, RPGame game, GameCharacter gC, JsonObject jTask){

		for (TaskFactory fac : taskFactories){
			if (fac.isThisType(taskType))
				return fac.constructTask(game,gC,jTask);
		}
		throw new RuntimeException("Given name of Quest" +
				" not recognized");
	}
	
	public abstract boolean checkComplete();
	
	/**
	 * Get attributes of implementation-specific subclass
	 * of the task
	 * @return JsonObject with subclass-specific attributes
	 */
	public abstract JsonObject getJsonAttributes();
}
