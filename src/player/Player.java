package player;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import utils.JsonUtil;
import actions.Action;
import actions.Attack;
import actions.Talk;

import com.google.gson.Gson;

import counters.Health;

public class Player extends CharacterDecorator {
	
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
		JsonUtil.JSONPlayerActions actions = gson.fromJson(json,
				JsonUtil.JSONPlayerActions.class);
		
		if (actions.walking == null || actions.attacking == null)
			throw new RuntimeException("Action undefined for player");

		character.getActions().add("walking",
				new Walking(new Action(character.getActions(), actions.walking)));
		character.getActions().add("attacking",
				new Attacking(new Attack(character.getActions(), actions.attacking)));
		character.getActions().add("talking",
				new Talking(new Talk(character.getActions(), actions.talking)));
		character.getActions().add("grabbing",
				new Grabbing(new Action(character.getActions(), actions.grabbing)));
		
		character.getCounters().add("health", new Health(character.getCounters(), 10));
	}
	
}
