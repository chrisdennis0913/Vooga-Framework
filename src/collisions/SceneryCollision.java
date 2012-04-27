package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class SceneryCollision extends CollisionGroup {

	public void collided(Sprite character, Sprite scenery) {
		pixelPerfectCollision = true;

		double ySeparation = Math.abs(character.getY() - (scenery.getY()));
		double maxsep = Math.abs(getCollisionShape2(scenery).getHeight()*1.25
				- getCollisionShape1(character).getHeight())/2;
		
		if (ySeparation <= maxsep) {
			revertPosition1();
		}
	}
}
