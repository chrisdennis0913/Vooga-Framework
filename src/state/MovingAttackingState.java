package state;

import gameCharacter.CharacterDecorator;
import ai.AbstractAttackAI;
import ai.AbstractMovementAI;
import ai.BooleanGameHeuristic;

public class MovingAttackingState implements State{
	private AbstractMovementAI myMovement;
	private AbstractAttackAI myAttack;
	private BooleanGameHeuristic heuristic;
	
	public MovingAttackingState(AbstractMovementAI move, AbstractAttackAI attack, BooleanGameHeuristic h)
	{
		myMovement = move;
		myAttack = attack;
		heuristic = h;
	}
	
	public void update(long elapsedTime, CharacterDecorator cD) 
	{
		if (myMovement != null)
			myMovement.update(elapsedTime);
		if (myAttack != null)
			myAttack.update(elapsedTime);
	}

	@Override
	public String getStatus() {
		return "Moving and Attacking";
	}

	@Override
	public boolean wantsActive() {
		return heuristic.getHeuristicBool();
	}

}
