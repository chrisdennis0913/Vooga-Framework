package ai;

import enemy.AbstractEnemy;
import app.RPGame;

public class SimpleDecisionTableAI extends AbstractDecisionTableAI{

	public SimpleDecisionTableAI(RPGame game, AbstractEnemy character) {
		super(game, character);
	}

	@Override
	public void constructTable() {
		dTable.addAttack("shooting");
		dTable.addAttack("poisoning");
		
		dTable.addHeuristicToAttack("shooting", new BoolDistanceFromTargetHeuristic(game, character.getCharacter(), 150,true), 10);
		dTable.addHeuristicToAttack("poisoning", new BoolDistanceFromTargetHeuristic(game, character.getCharacter(), 70,true), 30);
	}

	@Override
	public void onCollision() {
		//This AI is not collision based
	}

}
