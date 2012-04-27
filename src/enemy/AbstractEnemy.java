package enemy;

import gameCharacter.Attackable;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.HashMap;

import state.State;
import utils.Jsonable;
import app.RPGame;
import attacks.AbstractAttack;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import counters.EnemyHealth;

public abstract class AbstractEnemy extends CharacterDecorator implements Attackable, Jsonable{

	protected HashMap<String, AbstractAttack> attacks = new HashMap<String, AbstractAttack>();
	public static ArrayList<EnemyFactory> enemyFactories = new ArrayList<EnemyFactory>();
	private boolean alive = true;
	protected RPGame game;
	
	private State currentState;
	protected int moneyValue;
	private String name;

	public AbstractEnemy(RPGame game, GameCharacter character, String name, JsonObject jEnemy) {
		super(character);
		this.game = game;
		this.name = name;
		initAttacks(jEnemy);
		character.setDecorator(this);
	}
	
	public AbstractEnemy(RPGame game, GameCharacter character, String name, String[] attacks) {
		super(character);
		this.game = game;
		this.name = name;
		initAttacks(attacks);
		character.setDecorator(this);
	}
	
	static{
		enemyFactories.add(new TestEnemy.TestEnemyFactory());
	}

	public void initResources() {
		getCharacter().getCounters().add("health",
				new EnemyHealth(getCharacter().getCounters(), 2));
		initAI(null);
	}

	public abstract void initAI(String Json);
	
	protected void initAttacks(JsonObject json){
		for(JsonElement e: json.get("attacks").getAsJsonArray()){
			String attackName = e.getAsString();
			attacks.put(attackName, EnemyAttacks.createAttack(attackName, game, this));
		}
	}
	
	protected void initAttacks(String[] attackNames){
		for(String at:attackNames){
			attacks.put(at, EnemyAttacks.createAttack(at, game, this));
		}
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
	
	public static AbstractEnemy createEnemy(String enemyName, RPGame game, GameCharacter gameChar, JsonObject jEnemy){

		for (EnemyFactory fac : enemyFactories){
			if (fac.isThisType(enemyName))
				return fac.constructEnemy(game,gameChar,jEnemy);
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
		getCharacter().getGame().getPlayer().getCharacter().getInventory().get("money").add(moneyValue);
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
		json.add("directionsURL", new JsonPrimitive("rsc/config/enemy_directions.json"));
		return json;
	}
	
	/**
	 * Get attributes of implementation-specific subclass
	 * of the enemy
	 * @return JsonObject with subclass-specific attributes
	 */
	public abstract JsonObject getJsonAttributes();
}