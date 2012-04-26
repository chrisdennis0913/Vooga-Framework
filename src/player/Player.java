package player;

import java.awt.Graphics2D;

import utils.JsonUtil;
import utils.Jsonable;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import actions.Attack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player extends CharacterDecorator implements Jsonable {

	private static final long serialVersionUID = 1L;
	
	private String configURL;

	public Player(GameCharacter character, String configURL) {
		super(character);
		this.configURL = configURL;
		initResources();
	}

	public void initResources() {
		String json = JsonUtil.getJSON(configURL);
		constructActions(json);
		// TODO: constructInventory(inventoryJsonURL)
	}

	private void constructActions(String json) {
		Gson gson = new Gson();
		//JsonUtil.JSONPlayerActions actions = gson.fromJson(json,
				//JsonUtil.JSONPlayerActions.class);
		JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
		JsonObject actions = jsonObject.getAsJsonObject();

		JsonObject walking = actions.getAsJsonObject("walking");
		JsonObject attacking = actions.getAsJsonObject("attacking");
		if(actions.getAsJsonObject("walking") == null || actions.getAsJsonObject("attacking") == null)
			throw new RuntimeException("Action undefined for player");

		character.getActions().add("walking",
				new Walking(new Walk(character.getActions(), walking)));
		character.getActions().add("attacking",
				new Attacking(new Attack(character.getActions(), attacking)));
	}

	public void update(long elapsed) {
		super.update(elapsed);
		
	}

	@Override
	public JsonObject toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
}