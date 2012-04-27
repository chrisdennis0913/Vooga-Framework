package npc;

import gameCharacter.GameCharacter;

public abstract class NPCFactory {
	
	public abstract boolean isThisType(String npcName);
	public abstract NPC constructNPC(GameCharacter gameChar);

}
