package state;

import ai.BoolDistanceFromTargetHeuristic;
import gameCharacter.CharacterDecorator;

public class DiscoveryState implements State{

	boolean discoveredPlayer = false;
	BoolDistanceFromTargetHeuristic heuristic;
	
	public DiscoveryState(BoolDistanceFromTargetHeuristic h){
		heuristic = h;
	}
	@Override
	public void update(long elapsedTime, CharacterDecorator cD) {
		if(!discoveredPlayer)
			discoveredPlayer = heuristic.getHeuristicBool();
	}

	@Override
	public String getStatus() {
		return "Discovery";
	}

	@Override
	public boolean wantsActive() {
		return !discoveredPlayer;
	}

}
