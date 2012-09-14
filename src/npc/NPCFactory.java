package npc;

import gameCharacter.GameCharacter;
import com.google.gson.JsonElement;

public abstract class NPCFactory {
	
	public abstract boolean isThisType(String npcName);


	public abstract NPC constructNPC(GameCharacter gameChar, JsonElement jsonMovement);


}
