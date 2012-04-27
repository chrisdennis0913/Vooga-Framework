package quest;

import gameCharacter.GameCharacter;
import npc.NPC;

public abstract class QuestGiverNPC extends NPC implements QuestGiver
{

	private GameCharacter gC;
	
	public QuestGiverNPC(GameCharacter character)
	{
		super(character);
	}
	
	public void update(Quest qu)
	{
		if (qu.checkComplete())
		{
			gC.getJournal().completeQuest(qu);
			changeState();
		}
	}
	
	public abstract void changeState();
}
