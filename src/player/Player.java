package player;

import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;
import utils.JsonUtil;
import actions.Attack;
import actions.Talk;
import actions.Walk;

import com.google.gson.Gson;

public class Player extends CharacterDecorator {

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
		JsonUtil.JSONPlayerActions actions = gson.fromJson(json,
				JsonUtil.JSONPlayerActions.class);
		
		if (actions.walking == null || actions.attacking == null)
			throw new RuntimeException("Action undefined for player");

		character.getActions().add("walking",
				new Walking(new Walk(character.getActions(), actions.walking)));
		character.getActions().add("attacking",
				new Attacking(new Attack(character.getActions(), actions.attacking)));
		character.getActions().add("talking",
				new Talking(new Talk(character.getActions(), actions.talking)));
	}

	public void update(long elapsed) {
		super.update(elapsed);
	}
	
}
