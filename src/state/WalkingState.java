package state;

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

}
