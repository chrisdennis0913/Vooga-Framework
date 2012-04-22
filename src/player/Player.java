package player;

import utils.JsonUtil;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

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
		// eventually have constructInventory(inventoryJson)
		// currently constructed in GameCharacter
		
	}

	private void constructActions(String json) {
		Gson gson = new Gson();
		JsonUtil.JSONPlayerActions actions = gson.fromJson(json,
				JsonUtil.JSONPlayerActions.class);

		if (actions.walking != null || actions.attacking != null)
			throw new RuntimeException("Action undefined for player");

		character.getActions().add("walking",
				new Walking(character.getActions(), actions.walking));
		character.getActions().add("attacking",
				new Attacking(character.getActions(), actions.attacking));
	}

}
