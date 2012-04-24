package quest;

// think about removing concrete quests
// work on saving and loading
// how to choose a quest
// NPC states

// Design Doc: Moderate details about implementation. Mostly about how to use game 
// simple example and then really complicated version. Talk about flexibility and extendability.
// Design doc midday Thursday. 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.RPGame;

public abstract class Quest implements Observable
{
	protected ArrayList<Task> toDo;
	protected ArrayList<Task> done;
	protected boolean isActive = false;
	protected ArrayList<QuestGiver> observers;
	protected String description;
	
	public Quest(String description, Task... required)
	{
		List<Task> temp = Arrays.asList(required);
		toDo = new ArrayList<Task>(temp);
		done = new ArrayList<Task>();
		observers = new ArrayList<QuestGiver>();
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setActive(boolean b)
	{
		isActive = b;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public boolean checkComplete()
	{
		for (Task t: toDo)
		{
			if(t.checkComplete())
				done.add(t);
		}	
		return done.size() == toDo.size();
	}
	
	public void update()
	{
		for (QuestGiver qg: observers)
		{
			qg.update(this);
		}
	}
	
	public void addObserver(QuestGiver qu)
	{
		observers.add(qu);
	}


	public void removeObserver(QuestGiver qu)
	{
		observers.remove(qu);
	}
	
	public abstract void completeQuest(RPGame game2);
}
