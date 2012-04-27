package collisions;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;
import player.Attacking;

import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class EnemyCollision extends BasicCollisionGroup {
	
	private SystemTimer timer = new SystemTimer();
	private long separation;
	private long curTime;
	private Timer realTimer = new Timer(200);
	
	public EnemyCollision() {
		curTime = timer.getTime();
		realTimer.setActive(false);
	}

	public void collided(Sprite character, Sprite enemyChar) {
		separation = timer.getTime() - curTime;
		curTime = timer.getTime();
		
		pixelPerfectCollision = true;
		
		GameCharacter player = (GameCharacter) character;
		GameCharacter enemy = (GameCharacter) enemyChar;

		Attacking attacking = (Attacking) player.getActions().get("attacking");
		
		if (attacking.isActive()) {
			enemy.getCounters()
					.get("health")
					.decrease(attacking.getDamage((AbstractEnemy) enemy.getDecorator()));
			attacking.setActive(false);
		}
		
		
		if (!realTimer.isActive()) {
			realTimer.setActive(true);
			
			int damage = 1;
			if (attacking.getDamage((AbstractEnemy) enemy.getDecorator()) != 0)
				damage = attacking.getDamage((AbstractEnemy) enemy.getDecorator());
				
			player.getCounters()
					.get("health")
					.decrease(damage);
				
		} else if (realTimer.isActive()) {
			if (realTimer.action(separation)) {
				realTimer.refresh();
				realTimer.setActive(false);
			}
		}
	}
}
