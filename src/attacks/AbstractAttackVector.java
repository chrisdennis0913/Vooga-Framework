package attacks;

import app.RPGame;

import com.golden.gamedev.object.AnimatedSprite;

/**
 * Carrier of attack and attacker information. 
 * Contains a Sprite that upon collision with 
 * a game character, releases the attack on it.
 * 
 * @author jameshong
 *
 */
public abstract class AbstractAttackVector {

	protected static RPGame game;
	private AnimatedSprite vectorSprite;
	public AbstractAttackVector(RPGame game, AbstractVectorAttack owner, double x, double y, double speedX, double speedY){
		AbstractAttackVector.game = game;
		vectorSprite = loadVectorSprite();
		vectorSprite.setLocation(x, y);
		vectorSprite.setSpeed(speedX, speedY);
		owner.registerVectorSprite(vectorSprite);
	}
	
	public abstract AnimatedSprite loadVectorSprite();
}
