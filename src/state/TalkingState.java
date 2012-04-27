package state;

import gameCharacter.CharacterDecorator;

public class TalkingState implements State
{
	public TalkingState()
	{
	}

	public void update(long elapsedTime, CharacterDecorator cD) 
	{
	}
	
	public String getStatus()
	{
		return "Talking";
	}

	@Override
	public boolean wantsActive() {
		// TODO Auto-generated method stub
		return false;
	}
}
