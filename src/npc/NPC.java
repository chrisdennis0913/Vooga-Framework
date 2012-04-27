package npc;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import state.State;
import store.StoreManagerNPC;
import utils.Jsonable;
import dialogue.AbstractDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

public abstract class NPC extends CharacterDecorator implements Jsonable{
	/**
	 * Computer-generated serial ID number
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -5360689062786017503L;
	protected AbstractDialogue dialogue;
	private boolean alive;

	private State currentState;

	private static ArrayList<NPCFactory> NPCs = new ArrayList<NPCFactory>();

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

	public static NPC createNPC(String npcName, GameCharacter gameChar) {
		NPCs.add(new NPCTest1.NPCTest1Factory());
		NPCs.add(new StoreManagerNPC.StoreManager());
		for (NPCFactory npcFactory : NPCs) {
			if (npcFactory.isThisType(npcName)){
				return npcFactory.constructNPC(gameChar);
			}
		}
		throw new RuntimeException("Given name of NPC not recognized");
	}

	public void setDialogue(AbstractDialogue dialogue) {
		this.dialogue = dialogue;
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

	public NPC setCharacter(GameCharacter character) {
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
	public abstract JsonObject toJson();
}
