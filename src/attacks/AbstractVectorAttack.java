package attacks;

import gameCharacter.GameCharacter;
import calculators.DamageCalculator;
import collisions.AttackVectorCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import app.RPGame;

/**
 * Attack that uses vectors to convey the action.
 * For example: shooting projectiles or laser beams
 * @author jameshong
 *
 */
public abstract class AbstractVectorAttack extends AbstractAttack {
	
	private SpriteGroup vectors;
	protected DamageCalculator calculator;

	protected double vectorSpeedX = 0;
	protected double vectorSpeedY = 0;

	public AbstractVectorAttack(RPGame game, GameCharacter attacker, String name) {
		super(game, attacker, name);
		vectors = new SpriteGroup(getClass().getName());
		game.getField().addGroup(vectors);
		initCollisions();
		initCalculator();
	}

	protected abstract void initCalculator();

	public void initCollisions(){
		game.getField().addCollisionGroup(vectors, game.getField().getGroup("player"),
				new AttackVectorCollision(this));
	}

	public final void launchVector(double speedX, double speedY){
		launchVector(getAttackerX(),getAttackerY(), speedX, speedY);
	}
	
	public void setSpeed(double speedX, double speedY){
		vectorSpeedX = speedX;
		vectorSpeedY = speedY;
	}

	public void registerVectorSprite(Sprite s){
		vectors.add(s);
	}
	
	public abstract void launchVector(double x, double y, double speedX, double speedY);

	public void onCollision(Sprite vector, Sprite character) {
		GameCharacter player = (GameCharacter) character;
	
		vector.setActive(false);
		player.getCounters().get("health").decrease(calculateDamage());
	}

	public int calculateDamage() {
		return calculator.calculate();
	}
	
}
