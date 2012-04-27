package state;

import ai.BooleanGameHeuristic;
import gameCharacter.CharacterDecorator;

public class GuardState implements State{
	private BooleanGameHeuristic heuristic;

	public GuardState(BooleanGameHeuristic h){
		heuristic = h;
	}
	
	@Override
	public void update(long elapsedTime, CharacterDecorator cD) {
		cD.stop();
	}

	@Override
	public String getStatus() {
		return "Guarding";
	}

	@Override
	public boolean wantsActive() {
		return heuristic.getHeuristicBool();
	}

}
