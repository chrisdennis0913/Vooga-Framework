package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

/**
 * Heuristic that returns either 1 or 0 given
 * a condition. Examples:
 * "target is in slow terrain", distance thresholds
 * (target is closer than 30px)
 * @author jameshong
 *
 */
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
