package store;

import java.awt.Graphics2D;

import com.google.gson.JsonObject;

import state.State;
import state.TalkingState;

import dialogue.SimpleDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

import gameCharacter.GameCharacter;
import npc.NPC;
import npc.NPCFactory;

public class StoreManagerNPC extends NPC{
	ItemStore myStore;
	SimpleDialogue dialogue;
	private boolean hasTalked;

	public StoreManagerNPC(GameCharacter character) {
		super(character);
		myStore = new ItemStore(this.getCharacter(), this.getCharacter().getGame());
		dialogue = new SimpleDialogue("rsc/store/storeSpeech.txt");
		State s = new TalkingState();
		this.setCurrentState(s);
	}
	
	public void update (long elapsed){
		super.update(elapsed);
		//myStore.update(elapsed);
	}
	
	public void render(Graphics2D g){
		super.render(g);
		//myStore.render(g);
	}
	
	@Override
	public String getTalk(SimpleDialogueObject dialogueObject){
		if (!hasTalked){
			hasTalked = true;
			}
			else{
			dialogue.goToNextLine(dialogueObject);
			}
			return dialogue.getCurrentLine();
	}
	
	public void openStore() {
		myStore.openStore();
	}
	
	public ItemStore getStore(){
		return myStore;
	}
	
	public static class StoreManager extends NPCFactory{

		public boolean isThisType(String npcName) {
			return npcName.equals("StoreManagerNPC");
		}

		public NPC constructNPC(GameCharacter gameChar) {
			return new StoreManagerNPC(gameChar);
		}
		
	}

	@Override
	public JsonObject getJsonAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


}
