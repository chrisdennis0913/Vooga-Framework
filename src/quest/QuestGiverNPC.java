package quest;

import gameCharacter.GameCharacter;
import npc.NPC;

public abstract class QuestGiverNPC extends NPC implements QuestObserver
{

	private GameCharacter gC;
	protected boolean questComplete = false;
	
	public QuestGiverNPC(GameCharacter character)
	{
		super(character);
		gC = character;
	}
	
	public void update(Quest qu)
	{
		if (qu.checkComplete())
		{
			gC.getJournal().completeQuest(qu);
			questComplete = true;
		}
		if (questComplete == true)
		{
			changeState();
		}
	}
	
	public abstract void changeState();
}
