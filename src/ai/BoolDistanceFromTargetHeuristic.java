package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class BoolDistanceFromTargetHeuristic extends BooleanGameHeuristic{

	private double threshold;

	public BoolDistanceFromTargetHeuristic(RPGame game, GameCharacter character, double threshold){
		super(game, character, "boolDistanceFromTarget");
		this.threshold = threshold;
	}

	@Override
	public boolean getHeuristicBool() {
		double dist = attacker.getDistance(game.getPlayer().getCharacter());
		return dist > threshold;
	}
}
