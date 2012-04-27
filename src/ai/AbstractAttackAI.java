package ai;

import calculators.DamageCalculator;
import controllers.AttackController;
import enemy.TestEnemy;
import app.RPGame;
import attacks.AbstractAttack;

/**
 * Smart attack controller that chooses attacks based on an algorithm
 * 
 * @author jameshong
 *
 */
public abstract class AbstractAttackAI extends AttackController{

	protected RPGame game;
	protected TestEnemy character;
	
	public AbstractAttackAI(RPGame game, TestEnemy character){
		super(game, character.getCharacter());
		this.game = game;
		this.character = character;
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
