package collisions;

import enemy.AbstractEnemy;
import gameCharacter.GameCharacter;
import player.Attacking;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class EnemyCollision extends BasicCollisionGroup {
	
	public Timer timer = new Timer(200);
	
	public EnemyCollision() {
		timer.setActive(false);
	}

	public void collided(Sprite character, Sprite enemyChar) {
		
		pixelPerfectCollision = true;
		
		GameCharacter player = (GameCharacter) character;
		GameCharacter enemy = (GameCharacter) enemyChar;

		Attacking attacking = (Attacking) player.getActions().get("attacking");
		
		if (attacking.isActive()) {
			enemy.getCounters()
					.get("health")
					.decrease(attacking.getDamage((AbstractEnemy) enemy.getDecorator()));
		} else if (!timer.isActive()) {
			timer.setActive(true);
			player.getCounters()
					.get("health")
					.decrease(attacking.getDamage((AbstractEnemy) enemy.getDecorator()));
		}

		attacking.setActive(false);
	}
}
