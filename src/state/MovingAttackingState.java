package state;

import gameCharacter.CharacterDecorator;
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
	
	public void update(long elapsedTime, CharacterDecorator cD) 
	{	
		
		
		if (myMovement != null)
			myMovement.update(elapsedTime);
		
		if (myAttack != null)
			myAttack.update(elapsedTime);
	}

	public String getStatus()
	{
		return "Moving and Attacking";
	}
}