package collisions;

import gameCharacter.GameCharacter;
import npc.NPC;
import player.Talking;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class NPCCollision extends BasicCollisionGroup {

	public void collided(Sprite character, Sprite npcChar) {
		NPC npc = (NPC) ((GameCharacter) npcChar).getDecorator();
		setTalking((GameCharacter) character, npc);
		overlap(character, npcChar);
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
