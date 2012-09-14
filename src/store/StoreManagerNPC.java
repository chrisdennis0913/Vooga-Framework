package store;

import gameCharacter.GameCharacter;
import java.awt.Graphics2D;
import npc.NPC;
import npc.NPCFactory;
import state.State;
import state.TalkingState;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dialogue.SimpleDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

/**
 * Handles opening the store
 * 
 * @author zahavaalston
 *
 */

public class StoreManagerNPC extends NPC{
	ItemStore myStore;
	SimpleDialogue dialogue;
	private boolean hasTalked;

	public StoreManagerNPC(GameCharacter character) {
		super(character);
		myStore = new ItemStore(this, this.getCharacter().getGame());
		dialogue = new SimpleDialogue("rsc/store/storeSpeech.txt");
		State s = new TalkingState();
		this.setCurrentState(s);
	}
	
	public void update (long elapsed){
		super.update(elapsed);
	}
	
	/* (non-Javadoc)
	 * @see gameCharacter.CharacterDecorator#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g){
		super.render(g);
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

		//public CharacterDecorator constructNPC(GameCharacter gameChar) 

		public NPC constructNPC(GameCharacter gameChar, JsonElement jsonMovement) {

			return new StoreManagerNPC(gameChar);
		}

	}

	/**
	 * 
	 * @return a JsonObject containing the attributes specific to this subclass of
	 * NPC
	 */
	@Override
	public JsonObject getJsonAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

}
