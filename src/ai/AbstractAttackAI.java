package ai;

import gameCharacter.AttackController;
import gameCharacter.Enemy;
import app.RPGame;
import attacks.AbstractAttack;


public abstract class AbstractAttackAI extends AttackController{

	protected RPGame game;
	protected Enemy character;

	public AbstractAttackAI(RPGame game, Enemy character){
		super(game,character);
	}

	public AbstractAttack chooseAttack(){
		return pickBestAttack();
	}
	
	public abstract void update(long elapsedTime);
	public abstract boolean shouldAttack();

	/**
	 * Chooses the best attack for the current situation.
	 * Returns null if no attacks are available.
	 */
	public abstract AbstractAttack pickBestAttack();
	public abstract void onCollision();
	
}
