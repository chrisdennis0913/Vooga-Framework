package enemy;

import gameCharacter.Attackable;
import gameCharacter.AutomatedCharacter;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import java.util.HashMap;

import utils.JsonUtil;
import utils.Location;

import collisions.EnemyCollision;

import com.golden.gamedev.Game;

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
		initAttackAI();
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
		character.getControllers().add("AttackAI", new SimpleAttackAI(game,this));
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
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

}
