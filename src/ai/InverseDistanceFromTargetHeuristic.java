package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class InverseDistanceFromTargetHeuristic extends AbstractGameHeuristic {
	
	public InverseDistanceFromTargetHeuristic(RPGame game, GameCharacter character, double threshold){
		super(game, character, "inverseDistanceFromTarget");
	}

	@Override
	public double getHeuristic() {
		double dist = attacker.getDistance(game.getPlayer().getCharacter());
		if(dist > 0){
			return 1/dist;
		}
		else{
			return Double.MAX_VALUE;
		}
	}

}