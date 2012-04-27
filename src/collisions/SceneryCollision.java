package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class SceneryCollision extends CollisionGroup {

	public void collided(Sprite character, Sprite scenery) {
		pixelPerfectCollision = true;
		revertPosition1();
	}
}
