package state;


public class TalkingState implements State
{
	private boolean canTalk = true;

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
