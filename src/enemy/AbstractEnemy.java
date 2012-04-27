package enemy;

import java.util.HashMap;

import state.AttackingState;
import state.State;
import state.TalkingState;
import state.WalkingState;
import app.RPGame;
import attacks.AbstractAttack;
import gameCharacter.Attackable;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

public abstract class AbstractEnemy extends CharacterDecorator implements Attackable{

	protected HashMap<String, AbstractAttack> attacks = new HashMap<String, AbstractAttack>();

	protected String configURL;
	private boolean alive = true;
	protected RPGame game;
	private AttackingState atkState;
	private TalkingState talkState;
	private WalkingState walkState;
	private State currentState;

	public AbstractEnemy(GameCharacter character) {
		super(character);
		character.setDecorator(this);
	}

	public void initResources() {
		//String json = JsonUtil.getJSON(configURL);
		//initActions(json);
		initAttacks();
		initAttackAI();
		initMovementAI();
	}

	protected abstract void initMovementAI();
	protected abstract void initAttackAI();
	protected abstract void initAttacks();
	protected abstract void initActions(String json);

	public void update(long elapsedTime) {
		currentState.update(elapsedTime);
	}

	public HashMap<String, AbstractAttack> getAttacks() {
		return attacks;
	}

	public void setCurrentState(State s) {
		currentState = s;
	}

	@Override
	public int getHealth() {
		return character.getCounters().get("health").getCount();
	}

	@Override
	public void addToHealth(int delta) {
		character.getCounters().get("health").increase(delta);
		if(character.getCounters().get("health").isEmpty())
			alive = false;
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

}