package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class DistanceFromTargetHeuristic extends AbstractGameHeuristic {
	
	public DistanceFromTargetHeuristic(RPGame game, GameCharacter character, double threshold){
		super(game, character, "distanceFromTarget");
	}

	@Override
	public double getHeuristic() {
		double dist = attacker.getDistance(game.getPlayer());
		return dist;
	}

}
