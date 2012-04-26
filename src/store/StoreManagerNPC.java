package store;

import java.awt.Graphics2D;

import dialogue.AbstractDialogue.DialogueObject;
import dialogue.SimpleDialogue;

import gameCharacter.GameCharacter;
import npc.NPC;
import npc.NPCFactory;

public class StoreManagerNPC extends NPC{
	ItemStore myStore;
	SimpleDialogue dialogue;
	private boolean hasTalked;

	public StoreManagerNPC(GameCharacter character) {
		super(character);
		myStore = new ItemStore(this.getCharacter());
		dialogue = new SimpleDialogue("rsc/store/storeSpeech.txt");
	}
	
	public void update (long elapsed){
		
		super.update(elapsed);
		myStore.update(elapsed);
	}
	public void render(Graphics2D g){
		super.render(g);
		myStore.render(g);
	}
	
	public String getTalk(DialogueObject choice){
		if (!hasTalked){
			hasTalked = true;
			}
//			else{
//			dialogue.goToNextLine(new SimpleDialogue("").new SimpleDialogueObject());
//			}
			return dialogue.getCurrentLine();
	}
	
	public static class StoreManager extends NPCFactory{

		@Override
		public boolean isThisType(String npcName) {
			return npcName.equals("StoreManagerNPC");
		}

		@Override
		public NPC constructNPC(GameCharacter gameChar) {
			return new StoreManagerNPC(gameChar);
		}
		
	}


}
