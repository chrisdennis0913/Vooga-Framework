package collisions;

import gameCharacter.GameCharacter;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerProjectileCollision extends CollisionGroup {

	public void collided(Sprite enemyChar, Sprite projectile) {
		GameCharacter enemy = (GameCharacter) enemyChar;
		pixelPerfectCollision = true;

		enemy.getCounters().get("health").decrease(2);
		
		projectile.setActive(false);
	}

}
