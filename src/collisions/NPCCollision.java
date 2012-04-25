package collisions;

import gameCharacter.GameCharacter;
import npc.NPC;
import player.Talking;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class NPCCollision extends BasicCollisionGroup {

	public void collided(Sprite character, Sprite npc) {
		GameCharacter player = (GameCharacter) character;
		overlap(player, (NPC) npc);
	}

	protected void overlap(GameCharacter player, NPC npc) {
		
		Talking talking = (Talking) player.getActions().get("talking");
		talking.setNPC(npc);
		talking.setEnabled(true, false);

		double maxsep = npc.getImage().getHeight()
				- player.getImage().getHeight() / 6 * 5;
		double separation = player.getY() - npc.getY();

		if (separation <= maxsep) {
			player.setX(player.getOldX());
			player.setY(player.getOldY());
		}
	}

}
