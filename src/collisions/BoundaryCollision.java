package collisions;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionBounds;

public class BoundaryCollision extends CollisionBounds {

	public BoundaryCollision(Background level) {
		super(level);
	}

	public void collided(Sprite character) {
		character.setSpeed(character.getHorizontalSpeed() * (double) -1,
				character.getVerticalSpeed() * (double) -1);
		character.setX(character.getOldX());
		character.setY(character.getOldY());
	}

}
