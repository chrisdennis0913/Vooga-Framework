package npc;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import java.util.ArrayList;
import state.State;
import store.StoreManagerNPC;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dialogue.AbstractDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

public abstract class NPC extends CharacterDecorator{
	/**
	 * Computer-generated serial ID number
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -5360689062786017503L;
	protected AbstractDialogue dialogue;
	private boolean alive;

	private State currentState;

	private static ArrayList<NPCFactory> NPCs = new ArrayList<NPCFactory>();
	
	static{
		NPCs.add(new NPCTest1.NPCTest1Factory());
		NPCs.add(new StoreManagerNPC.StoreManager());
	}

	/**
	 * constructs a non-player character based on the information at the
	 * configuration URL given
	 * 
	 * @param game
	 * @param loc
	 * @param configURL
	 */
	public NPC(GameCharacter character) {
		super(character);
		character.setDecorator(this);
	}

	/*public static CharacterDecorator createNPC(String npcName, GameCharacter gameChar) {
		NPCs.add(new NPCTest1.NPCTest1Factory());
		NPCs.add(new StoreManagerNPC.StoreManager());}*/

	public static NPC createNPC(String npcName, GameCharacter gameChar, JsonElement jsonMovement) {

		for (NPCFactory npcFactory : NPCs) {
			if (npcFactory.isThisType(npcName)){
				return npcFactory.constructNPC(gameChar, jsonMovement);
			}
		}
		throw new RuntimeException("Given name of NPC not recognized");
	}

	public void setDialogue(AbstractDialogue dialogue) 
	{
		this.dialogue = dialogue;
	}

	public String getTalk() {
		if (dialogue == null)
			return null;
		return dialogue.getCurrentLine();
	}

	public abstract String getTalk(SimpleDialogueObject simpleDialogueObject);

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAlive() {
		return alive;
	}

	public void update(long elapsed) {
		currentState.update(elapsed, this);
	}

	public CharacterDecorator setCharacter(GameCharacter character) {
		this.character = character;
		return this;
	}

	public void setCurrentState(State s) {
		currentState = s;
	}

	public State getCurrentState() {
		return currentState;
	}

	public boolean hasDialogue() {
		return (dialogue != null);
	}
	
	@Override
	public JsonObject toJson(){
		JsonObject json = getJsonAttributes();
		json.add("name", new JsonPrimitive(name));
		JsonArray location = new JsonArray();
		location.add(new JsonPrimitive(getCharacter().getX()));
		location.add(new JsonPrimitive(getCharacter().getY()));
		json.add("location", location);
		json.add("directions", new JsonPrimitive("rsc/config/payer_directions.json"));
		return json;		
	}
	
	/** 
	 * Get attributes of implementation-specific subclass of NPC
	 * 
	 * @return JsonObject with subclass specific attributes
	 */
	public abstract JsonObject getJsonAttributes();
}
