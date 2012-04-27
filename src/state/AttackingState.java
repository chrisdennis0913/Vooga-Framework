package state;

import gameCharacter.CharacterDecorator;
import ai.AbstractAttackAI;
import ai.BooleanGameHeuristic;

public class AttackingState implements State{
	private AbstractAttackAI myAttack;
	private BooleanGameHeuristic heuristic;

	public AttackingState(AbstractAttackAI attack, BooleanGameHeuristic h)
	{
		myAttack = attack;
		heuristic = h;
	}

	public void update(long elapsedTime, CharacterDecorator cD) 
	{
		if (myAttack != null)
			myAttack.update(elapsedTime);
	}

	@Override
	public String getStatus() {
		return "Attacking";
	}

	@Override
	public boolean wantsActive() {
		return heuristic.getHeuristicBool();
	}

}
