package ai;

import enemy.AbstractEnemy;
import enemy.TestEnemy;
import app.RPGame;
import attacks.AbstractAttack;


public class SimpleAttackAI extends AbstractAttackAI{

	public SimpleAttackAI(RPGame game, AbstractEnemy character) {
		super(game, character);
	}

	@Override
	public void update(long elapsedTime) {
		//if(character.isAlive()){
			AbstractAttack choice = pickBestAttack();
			//if(choice != null)
				choice.performAttack(elapsedTime);
		//}
	}

	@Override
	public boolean shouldAttack() {
		return true;
	}

	@Override
	public AbstractAttack pickBestAttack() {
		return character.getAttacks().values().iterator().next();
	}

	@Override
	public void onCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractAttack getCurrentAttack() {
		return character.getAttacks().values().iterator().next();
	}

	@Override
	public boolean isAttacking() {
		return false;
	}

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		
	}

}
