package npc;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

public abstract class NPCFactory {
	
	public abstract boolean isThisType(String npcName);
	public abstract CharacterDecorator constructNPC(GameCharacter gameChar);

}
