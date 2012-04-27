package attacks;

import calculators.DamageCalculator;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;

import app.RPGame;
import attacks.AbstractVectorAttack;

public class ShootingAttack extends AbstractVectorAttack{

	private Timer timer;
	private DamageCalculator calculator;
	
	public ShootingAttack(RPGame game, AbstractEnemy enemy, String name) {
		super(game, enemy.getCharacter(), name);
		timer = new Timer(1000);
		vectorSpeedX = 0.1;
		vectorSpeedY = 0.1;
		calculator = new DamageCalculator(game.getPlayer().getCharacter().getCounters(), 
				enemy.getCharacter().getCounters());
	}
	
	public void launchVector(double x, double y, double speedX, double speedY) {
		new ShotVector(game, this, x, y, speedX, speedY);
	}

	public void onCollision(Sprite vector, Sprite character) {
		GameCharacter player = (GameCharacter) character;
		
		vector.setActive(false);
		player.getCounters().get("health").decrease(calculateDamage());
	}

	public boolean isAvailable(long elapsedTime) {
		return timer.action(elapsedTime);
	}

	public void performAttack(long elapsedTime) {
		if (isAvailable(elapsedTime)){
			double speedX = 0, speedY = 0;
			if(Math.abs(target.getX() - attacker.getX()) > Math.abs(target.getY() - attacker.getY())){
					if(target.getX() > attacker.getX())
						speedX = vectorSpeedX;
					else
						speedX = -vectorSpeedX;
			}
			else{
				if(target.getY() > attacker.getY())
					speedY = vectorSpeedY;
				else
					speedY = -vectorSpeedY;
			}
			launchVector(speedX,speedY);			
		}
	}

	public int calculateDamage() {
		return calculator.calculate();
	}
	
	public static class ShootingAttackFactory extends AttackFactory{

		@Override
		public boolean isThisType(String attackName) {
			return "shooting".equals(attackName);
		}

		@Override
		public AbstractAttack constructAttack(RPGame game,
				AbstractEnemy enemy) {
			return new ShootingAttack(game,enemy,"shooting");
		}
		
	}

}
