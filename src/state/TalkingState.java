package state;

import dialogue.AbstractDialogue;


public class TalkingState implements State
{
	private boolean canTalk = true;
	private AbstractDialogue myDialogue;
	
	public TalkingState(AbstractDialogue dialog)
	{
		myDialogue = dialog;
	}

	public void update(long elapsedTime) 
	{
	}

	public boolean talkable() {
		return canTalk;
	}

	public void setTalkable(boolean status) 
	{
		canTalk = status;
	}
	
	public String getStatus()
	{
		return "Talking";
	}
}
