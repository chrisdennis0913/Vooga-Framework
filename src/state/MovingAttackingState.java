package state;

import ai.AbstractAttackAI;
import ai.AbstractMovementAI;

public class MovingAttackingState implements State
{
	private AbstractMovementAI myMovement;
	private AbstractAttackAI myAttack;
	
	public MovingAttackingState(AbstractMovementAI move, AbstractAttackAI attack)
	{
		myMovement = move;
		myAttack = attack;
	}
	
	public void update(long elapsedTime) 
	{
		if (myMovement == null)
			throw new RuntimeException("No movement defined");
		if (myAttack == null)
			throw new RuntimeException("No attack defined");
		
		myMovement.update(elapsedTime);
		myAttack.update(elapsedTime);
	}

	public String getStatus()
	{
		return "Moving and Attacking";
	}
}