package state;

import gameCharacter.CharacterDecorator;
import ai.AbstractMovementAI;
import ai.BooleanGameHeuristic;

public class MovingState implements State{
	private AbstractMovementAI myMovement;
	private BooleanGameHeuristic heuristic;
	
	public MovingState(AbstractMovementAI move, BooleanGameHeuristic h)
	{
		myMovement = move;
		heuristic = h;
	}
	
	public void update(long elapsedTime, CharacterDecorator cD) 
	{
		if (myMovement != null)
			myMovement.update(elapsedTime);
	}

	@Override
	public String getStatus() {
		return "Moving";
	}

	@Override
	public boolean wantsActive() {
		return heuristic.getHeuristicBool();
	}

}
