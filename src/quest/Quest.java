package quest;

import gameCharacter.GameCharacter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import app.RPGame;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class Quest implements Observable
{
	protected ArrayList<Task> toDo;
	protected ArrayList<Task> done;
	protected boolean isActive = false;
	protected ArrayList<QuestObserver> observers;
	protected String description;
	protected static ArrayList<QuestFactory> questFactories = new ArrayList<QuestFactory>();
	
	static
	{
		questFactories.add(new RewardQuest.RewardQuestFactory());
	}
	
	public Quest(String description, Task... required)
	{
		List<Task> temp = Arrays.asList(required);
		toDo = new ArrayList<Task>(temp);
		done = new ArrayList<Task>();
		observers = new ArrayList<QuestObserver>();
		this.description = description;
	}
	
	public Quest(String description)
	{
		toDo = new ArrayList<Task>();
		done = new ArrayList<Task>();
		observers = new ArrayList<QuestObserver>();
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void addTask(Task t)
	{
		toDo.add(t);
	}
	
	public void removeTask(Task t)
	
	{
		toDo.remove(t);
	}
	
	public void setActive(boolean b)
	{
		isActive = b;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public String getStatus()
	{
		int completed = done.size();
		int left = toDo.size();
		
		return "You have completed: " + completed + " out of " + (completed + left) + " tasks." ;
	}
	
	public boolean checkComplete()
	{
		for (Task t: toDo)
		{
			if(t.checkComplete())
			{
				done.add(t);
			}
		}	
		return done.size() == toDo.size();
	}
	
	public void update()
	{
		for (QuestObserver qg: observers)
		{
			qg.update(this);
		}
	}
	
	public void addObserver(QuestObserver qu)
	{
		observers.add(qu);
	}


	public void removeObserver(QuestObserver qu)
	{
		observers.remove(qu);
	}
	
	public static Quest createQuest(String questType, RPGame game, Task[] tasks, JsonObject jQuest){

		for (QuestFactory fac : questFactories){
			if (fac.isThisType(questType))
				return fac.constructQuest(game,tasks,jQuest);
		}
		throw new RuntimeException("Given name of Quest" +
				" not recognized");
	}
	
	public JsonObject toJson()
	{
		JsonObject json = getJsonAttributes();
		json.add("name", new JsonPrimitive(description));
		
		JsonArray jsonTasks = new JsonArray();
		
		for (Task t: toDo)
		{
			jsonTasks.add(t.toJson());
		}
		
		json.add("tasks", jsonTasks);
		
		return json;
	}
	
	public abstract void completeQuest(GameCharacter gC);
	
	/**
	 * Get attributes of implementation-specific subclass
	 * of the quest
	 * @return JsonObject with subclass-specific attributes
	 */
	
	public abstract JsonObject getJsonAttributes();
}
