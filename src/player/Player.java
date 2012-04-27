package player;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import utils.JsonUtil;
import utils.Jsonable;
import actions.Action;
import actions.Attack;
import actions.Talk;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import counters.Health;

public class Player extends CharacterDecorator implements Jsonable {
	
	private String configURL;

	public Player(GameCharacter character, String configURL) {
		super(character);
		this.configURL = configURL;
		initResources();
	}

	public void initResources() {
		JsonObject actions = JsonUtil.getJSON(configURL);
		constructActions(actions);
	}

	private void constructActions(JsonObject actions) {
		JsonObject walking = actions.get("walking").getAsJsonObject();
		JsonObject attacking = actions.get("attacking").getAsJsonObject();
		JsonObject talking = actions.get("talking").getAsJsonObject();
		JsonObject grabbing = actions.get("grabbing").getAsJsonObject();
		
		if(walking == null || attacking == null)
			throw new RuntimeException("Action undefined for player");
		
		character.getActions().add("walking",
				new Walking(new Action(character.getActions(), walking)));
		character.getActions().add("attacking",
				new Attacking(new Attack(character.getActions(), attacking)));
		character.getActions().add("talking",
				new Talking(new Talk(character.getActions(), talking)));
		character.getActions().add("grabbing",
				new Grabbing(new Action(character.getActions(), grabbing)));
		
		character.getCounters().add("health", new Health(character.getCounters(), 10));
	}

	@Override
	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		JsonArray location = new JsonArray();
		location.add(new JsonPrimitive(getCharacter().getX()));
		location.add(new JsonPrimitive(getCharacter().getY()));
		json.add("directionsURL", new JsonPrimitive("rsc/config/player_directions.json"));
		json.add("actionsURL", new JsonPrimitive("rsc/config/player_direction.json"));		
		return json;
	}
	
}
