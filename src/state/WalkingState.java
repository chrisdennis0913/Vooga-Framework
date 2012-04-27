package state;

import gameCharacter.CharacterDecorator;
import ai.AbstractMovementAI;

public class WalkingState implements State
{
	private AbstractMovementAI myAI;
	
	public WalkingState (AbstractMovementAI ai)
	{
		myAI = ai;
	}
	
	public void update(long elapsedTime) 
	{
		myAI.update(elapsedTime);
	}

	@Override
	public void update(long elapsedTime, CharacterDecorator cD) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
