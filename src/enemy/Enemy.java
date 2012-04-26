package enemy;

import gameCharacter.Attackable;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import java.util.HashMap;

import state.AttackingState;
import state.State;
import state.TalkingState;
import state.WalkingState;


import com.golden.gamedev.Game;

import ai.PathFindingAI;
import ai.SimpleAttackAI;
import app.RPGame;
import attacks.AbstractAttack;
import attacks.ShootingAttack;

public class Enemy extends CharacterDecorator implements Attackable{

	private HashMap<String, AbstractAttack> attacks = new HashMap<String, AbstractAttack>();
	//private EnemyCollision collision;
	private String configURL;
	private boolean alive = true;
	private RPGame game;
	private AttackingState atkState;
	private TalkingState talkState;
	private WalkingState walkState;
	
	private State currentState;
	
	public Enemy(RPGame game, GameCharacter character, String configURL) {
		super(character);
		this.configURL = configURL;
		this.game = game;
		initResources();
	}
	
	public void initResources(){
		//String json = JsonUtil.getJSON(configURL);
		//constructActions(json);
		initAttacks();
		//initAttackAI();
		initMovementAI();
	}
	
	private void constructActions(String json) {
		// TODO Auto-generated method stub
	}

	//move to json
	public void initAttacks() {
		attacks.put("shooting",new ShootingAttack(game,this,"shooting"));
	}
	
	//move to json
	private void initAttackAI(){
		setCurrentState(new AttackingState(new SimpleAttackAI(game,this)));
	}
	

	public void update(long elapsedTime)
	{
		currentState.update(elapsedTime);
	}
	private void initMovementAI(){
		setCurrentState(new WalkingState(new PathFindingAI(game, this.getCharacter())));
	}
	
	public HashMap<String, AbstractAttack> getAttacks() {
		return attacks;
	}
/*
	public void attack(AbstractAttack at, long elapsedTime){
		throw new RuntimeException("Enemy attack() undefined");
	}
*/
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
	
	public void setCurrentState(State s)
	{
		currentState = s;
	}

}
