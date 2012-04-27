package state;

import ai.AbstractMovementAI;

public class MovingState implements State
{
	private AbstractMovementAI myAI;
	
	public MovingState (AbstractMovementAI ai)
	{
		myAI = ai;
	}
	
	public void update(long elapsedTime) 
	{
		myAI.update(elapsedTime);
	}

	public String getStatus()
	{
		return "Moving";
	}
}
