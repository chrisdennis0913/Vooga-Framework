package quest;

//An interface to be implemented by quests. 
//QuestGivers will observe quests in order to decide what dialogue to share with the player

public interface Observable 
{
	 public void addObserver(QuestObserver qu);
	 
	 public void removeObserver(QuestObserver qu);
}
