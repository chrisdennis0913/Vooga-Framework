package attacks;

import calculators.DamageCalculator;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import enemy.Enemy;
import gameCharacter.GameCharacter;

import app.RPGame;
import attacks.AbstractVectorAttack;

public class ShootingAttack extends AbstractVectorAttack{

	private Timer timer;
	private DamageCalculator calculator = new DamageCalculator(null, null);
	
	public ShootingAttack(RPGame game, Enemy enemy, String name) {
		super(game, enemy.getCharacter(), name);
		timer = new Timer(1000);
		vectorSpeedX = 0.1;
		vectorSpeedY = 0.1;
	}

	@Override
	public void launchVector(double x, double y, double speedX, double speedY) {
		new ShotVector(game, this, x, y, speedX, speedY);
	}

	@Override
	public void onCollision(Sprite vector, Sprite character) {
		GameCharacter player = (GameCharacter) character;
		
		vector.setActive(false);
		player.getCounters().get("health").decrease(calculateDamage(0));
	}

	@Override
	public boolean isAvailable(long elapsedTime) {
		return timer.action(elapsedTime);
	}

	@Override
	public void performAttack(long elapsedTime) {
		if (isAvailable(elapsedTime))
			launchVector();
	}

	@Override
	public int calculateDamage(long elapsedTime) {
		return calculator.calculate();
	}

}
