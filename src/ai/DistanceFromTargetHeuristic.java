package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class DistanceFromTargetHeuristic extends AbstractGameHeuristic {

	double threshold;
	private static double DEFAULT_THRESHOLD = 0.5;
	
	public DistanceFromTargetHeuristic(RPGame game, GameCharacter character){
		this(game, character, DEFAULT_THRESHOLD);
	}
	
	public DistanceFromTargetHeuristic(RPGame game, GameCharacter character, double threshold){
		super(game, character, "playerFarFromEnemy");
		this.threshold = threshold;
	}

	@Override
	public boolean getHeuristicBool() {
		double dist = attacker.getDistance(game.getPlayer());
		return (dist > threshold);
	}

}
