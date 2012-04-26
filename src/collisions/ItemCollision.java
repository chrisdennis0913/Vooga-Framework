package collisions;

import player.Grabbing;
import inventory.Item;
import gameCharacter.GameCharacter;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ItemCollision extends BasicCollisionGroup {

	@Override
	public void collided(Sprite character, Sprite it) {
		GameCharacter player = (GameCharacter) character;
		Item item = (Item) it;
		
		Grabbing grabbing = (Grabbing) player.getActions().get("grabbing");
		grabbing.setEnabled(true, false);
		grabbing.setItem(item);
	}
	

}
