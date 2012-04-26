package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class BooleanGameHeuristic extends AbstractGameHeuristic{

	public BooleanGameHeuristic(RPGame game, GameCharacter character,
			String conditionName) {
		super(game, character, conditionName);
	}

	public abstract boolean getHeuristicBool();
	
	public double getHeuristic(){
		return getHeuristicBool() ? 1.0 : 0.0;
	}
}
