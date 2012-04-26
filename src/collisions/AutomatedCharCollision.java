package collisions;

import gameCharacter.GameCharacter;
import npc.NPC;
import player.Talking;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class AutomatedCharCollision extends BasicCollisionGroup {

	public void collided(Sprite character, Sprite npc) {
		setTalking((GameCharacter) character, (NPC) npc);
		overlap(character, npc);
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
