package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class SceneryCollision extends CollisionGroup {

	public void collided(Sprite character, Sprite scenery) {
		pixelPerfectCollision = true;

		double ySeparation = Math.abs(character.getY() - (scenery.getY()));
		double maxYsep = Math.abs(getCollisionShape2(scenery).getHeight()*1.25
				- getCollisionShape1(character).getHeight())/2;
		
		if (ySeparation <= maxYsep) {
			revertPosition1();
		}
		
		double xSeparation = Math.abs(character.getX() - (scenery.getX()));
		double maxXsep = Math.abs(getCollisionShape2(scenery).getHeight()*1.25
				- getCollisionShape1(character).getHeight())/2;
		
		if (xSeparation <= maxXsep) {
			revertPosition1();
		}
	}
}
