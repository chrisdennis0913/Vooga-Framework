package ai;

import enemy.AbstractEnemy;
import app.RPGame;
import attacks.AbstractAttack;

public class TestDecisionTableAI extends AbstractDecisionTableAI{

	public TestDecisionTableAI(RPGame game, AbstractEnemy character) {
		super(game, character);
	}

	@Override
	public void constructTable() {
		dTable.addAttack("shooting");
		
		dTable.addHeuristicToAttack("shooting", new BoolDistanceFromTargetHeuristic(game, character.getCharacter(), 150), 10);
		
	}

	@Override
	public void initResources() {
	}

	@Override
	public void onCollision() {
		
	}

	@Override
	public AbstractAttack getCurrentAttack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAttacking() {
		// TODO Auto-generated method stub
		return false;
	}

}
