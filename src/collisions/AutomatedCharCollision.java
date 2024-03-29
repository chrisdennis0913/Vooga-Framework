package collisions;

import gameCharacter.GameCharacter;
import npc.NPC;
import player.Talking;
import store.ItemStore;
import store.StoreManagerNPC;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class AutomatedCharCollision extends BasicCollisionGroup {
	
	ItemStore store;

	public void collided(Sprite character, Sprite npcChar) {
		NPC npc = (NPC) ((GameCharacter) npcChar).getDecorator();
		if (npc.hasDialogue()){
			setTalking((GameCharacter) character, npc);
		}
		if (npc.getClass().equals(StoreManagerNPC.class)){
			store = ((StoreManagerNPC) npc).getStore();
			System.out.println("Got to store");
			store.openStore();
		}
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
