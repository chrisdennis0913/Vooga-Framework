package collisions;

import player.Talking;
import gameCharacter.GameCharacter;
import npc.NPC;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class StoreCollision extends BasicCollisionGroup{

	@Override
	public void collided(Sprite character, Sprite store) {
		NPC npc = (NPC) ((GameCharacter) store).getDecorator();
		setTalking((GameCharacter) character, npc);
		overlap(character, store);	
	}
	
	protected void overlap(Sprite player, Sprite npc) {		
		pixelPerfectCollision = true;

		double separation = Math.abs(player.getY() - npc.getY());

		if (separation <= 2) {
			player.setX(player.getOldX());
			player.setY(player.getOldY());
		}
	}
	
	private void setTalking(GameCharacter player, NPC npc) {
		Talking talking = (Talking) player.getActions().get("talking");
		talking.setNPC(npc);
		talking.setEnabled(true, false);
	}

}
