package collisions;

import player.Attacking;
import enemy.Enemy;
import gameCharacter.GameCharacter;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class EnemyCollision extends BasicCollisionGroup {

	public void collided(Sprite character, Sprite enemyChar) {
		GameCharacter player = (GameCharacter) character;
		GameCharacter enemy = (GameCharacter) enemyChar;

		Attacking attacking = (Attacking) player.getActions().get("attacking");
		if (attacking.isEnabled()) {
			enemy.getCounters()
					.get("health")
					.decrease(attacking.getDamage((Enemy) enemy.getDecorator()));
		}
		else {
			player.getCounters()
			.get("health")
			.decrease(attacking.getDamage((Enemy) enemy.getDecorator()));
		}
	}

}
