package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class BoolDistanceFromTargetHeuristic extends BooleanGameHeuristic{

	private double threshold;
	private boolean trueIfSmaller;

	public BoolDistanceFromTargetHeuristic(RPGame game, GameCharacter character, double threshold, boolean trueIfSmaller){
		super(game, character, "boolDistanceFromTarget");
		this.threshold = threshold;
		this.trueIfSmaller = trueIfSmaller;
	}

	@Override
	public boolean getHeuristicBool() {
		double dist = attacker.getDistance(game.getPlayer().getCharacter());
		if(trueIfSmaller)
			return (dist < threshold);
		else
			return (dist > threshold);
	}
}
