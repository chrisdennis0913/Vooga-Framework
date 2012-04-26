package store;

import java.awt.Graphics2D;

import dialogue.AbstractDialogue.DialogueObject;
import dialogue.SimpleDialogue;

import gameCharacter.GameCharacter;
import app.RPGame;
import npc.NPC;

public class StoreManagerNPC extends NPC{
	ItemStore myStore;
	SimpleDialogue dialogue;
	private boolean hasTalked;

	public StoreManagerNPC(RPGame game, GameCharacter character) {
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


}
