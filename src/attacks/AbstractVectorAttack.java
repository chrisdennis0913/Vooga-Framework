package attacks;

import gameCharacter.GameCharacter;
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
	
	protected double vectorSpeedX = 0;
	protected double vectorSpeedY = 0;

	public AbstractVectorAttack(RPGame game, GameCharacter attacker, String name) {
		super(game, attacker, name);
		vectors = new SpriteGroup(getClass().getName());
		game.getField().addGroup(vectors);
		initCollisions();
	}

	public void initCollisions(){
		game.getField().addCollisionGroup(vectors, game.getField().getGroup("player"),
				new AttackVectorCollision(this));
	}

	public final void launchVector(){
		launchVector(getAttackerX(),getAttackerY(), vectorSpeedX, vectorSpeedY);
	}
	
	public void setSpeed(double speedX, double speedY){
		vectorSpeedX = speedX;
		vectorSpeedY = speedY;
	}

	public void registerVectorSprite(Sprite s){
		vectors.add(s);
	}
	
	public abstract void launchVector(double x, double y, double speedX, double speedY);

	public abstract void onCollision(Sprite arg0, Sprite arg1);
	
}
