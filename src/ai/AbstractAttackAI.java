package ai;

import app.RPGame;
import attacks.AbstractAttack;
import controllers.AttackController;
import enemy.AbstractEnemy;

/**
 * Smart attack controller that chooses attacks based on an algorithm
 * 
 * @author jameshong
 *
 */
public abstract class AbstractAttackAI extends AttackController{

	protected RPGame game;
	protected AbstractEnemy character;
	
	public AbstractAttackAI(RPGame game, AbstractEnemy character){
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
