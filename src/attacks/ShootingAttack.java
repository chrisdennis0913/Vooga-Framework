package attacks;

import calculators.DamageCalculator;
import calculators.RandomCalculator;

import com.golden.gamedev.object.Timer;

import enemy.AbstractEnemy;

import app.RPGame;
import attacks.AbstractVectorAttack;

public class ShootingAttack extends AbstractVectorAttack{

	private Timer timer;
	
	public ShootingAttack(RPGame game, AbstractEnemy enemy, String name) {
		super(game, enemy.getCharacter(), name);
		timer = new Timer(1000);
		vectorSpeedX = 0.1;
		vectorSpeedY = 0.1;
		calculator = new RandomCalculator(game.getPlayer().getCharacter().getCounters(), 
				enemy.getCharacter().getCounters());
	}
	
	public void launchVector(double x, double y, double speedX, double speedY) {
		new ShotVector(game, this, x, y, speedX, speedY);
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
	
	@Override
	protected void initCalculator() {
		calculator = new RandomCalculator(game.getPlayer().getCharacter().getCounters(),
				attacker.getCounters());
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
