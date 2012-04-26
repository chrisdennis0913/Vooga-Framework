package collisions;

import attacks.AbstractVectorAttack;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;


public class AttackVectorCollision extends BasicCollisionGroup{

	AbstractVectorAttack owner;
	
	public AttackVectorCollision(AbstractVectorAttack owner){
		this.owner = owner;
		pixelPerfectCollision = true;
	}
	
	public void collided(Sprite arg0, Sprite arg1) {
		owner.onCollision(arg0,arg1);
	}

}
