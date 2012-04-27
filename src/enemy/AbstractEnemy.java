package enemy;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import counters.EnemyHealth;

import state.State;

import state.MovingAttackingState;
import utils.JsonUtil;
import utils.Jsonable;
import ai.GreedyPathFindingAI;
import ai.SimpleAttackAI;

import app.RPGame;
import attacks.AbstractAttack;
import gameCharacter.Attackable;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

public abstract class AbstractEnemy extends CharacterDecorator implements Attackable, Jsonable{

	protected HashMap<String, AbstractAttack> attacks = new HashMap<String, AbstractAttack>();
	public static ArrayList<EnemyFactory> enemyFactories = new ArrayList<EnemyFactory>();
	protected String configURL;
	private boolean alive = true;
	protected RPGame game;

	private State currentState;
	protected int moneyValue;
	private String name;

	public AbstractEnemy(GameCharacter character, String name) {
		super(character);
		this.name = name;
		character.setDecorator(this);
	}
	
	static{
		enemyFactories.add(new TestEnemy.TestEnemyFactory());
	}

	public void initResources() {
		//JsonObject json = JsonUtil.getJSON(configURL);
		//initAttacks(json);
		initAttacks();
		getCharacter().getCounters().add("health",
				new EnemyHealth(getCharacter().getCounters(), 2));
		initAI();
	}

	
	public void initAI()
	{
		setCurrentState(new MovingAttackingState(new GreedyPathFindingAI(game, this.getCharacter()), new SimpleAttackAI(game,this)));
	}

	protected abstract void initAttacks();
	
	protected void initAttacks(JsonObject json){
		//TODO: implement HashMap of attacks
		//json.get("attacks")
	}

	public void update(long elapsedTime) {
		currentState.update(elapsedTime, this);
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
	
	public static AbstractEnemy createEnemy(String enemyName, RPGame game, GameCharacter gameChar, String configURL){

		for (EnemyFactory fac : enemyFactories){
			if (fac.isThisType(enemyName))
				return fac.constructEnemy(game,gameChar,configURL);
		}
		throw new RuntimeException("Given name of NPC not recognized");
	}

	@Override
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	public void uponDeath() {
		//TODO: enable when money item is ready
		//getCharacter().getGame().getPlayer().getCharacter().getInventory().get("money").add(moneyValue);
	}
	
	@Override
	public JsonObject toJson() {
		JsonObject json = getJsonAttributes();
		json.add("name", new JsonPrimitive(name));
		JsonArray jsonAttacks = new JsonArray();
		for(AbstractAttack at : this.attacks.values()){
			jsonAttacks.add(new JsonPrimitive(at.getName()));
		}
		json.add("attacks", jsonAttacks);
		JsonArray location = new JsonArray();
		location.add(new JsonPrimitive(getCharacter().getX()));
		location.add(new JsonPrimitive(getCharacter().getY()));
		json.add("location", location);
		json.add("directionsURL", new JsonPrimitive(configURL));
		json.add("actionsURL", new JsonPrimitive("rsc/config/player_direction.json"));		
		return json;
	}
	
	/**
	 * Get attributes of implementation-specific subclass
	 * of the enemy
	 * @return JsonObject with subclass-specific attributes
	 */
	public abstract JsonObject getJsonAttributes();
}