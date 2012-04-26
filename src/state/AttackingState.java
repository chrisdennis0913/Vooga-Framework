package state;

import ai.AbstractAttackAI;
import gameCharacter.CharacterDecorator;

public class AttackingState implements State 
{
	
	private AbstractAttackAI myAI;
	
	public AttackingState (AbstractAttackAI ai)
	{
		myAI = ai;
	}

	public void update(long elapsedTime)
	{
		myAI.update(elapsedTime);
	}
	
	public void setAI(AbstractAttackAI ai)
	{
		myAI = ai;
	}
	
}
