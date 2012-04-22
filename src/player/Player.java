package player;

import utils.JsonUtil;
import gameCharacter.CharacterDecorator;
import gameCharacter.GameCharacter;

import com.google.gson.Gson;

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
		// eventually have constructInventory(inventoryJson)
		
	}

	private void constructActions(String json) {
		Gson gson = new Gson();
		JsonUtil.JSONPlayerActions actions = gson.fromJson(json,
				JsonUtil.JSONPlayerActions.class);

		if (actions.walking != null)
			character.getActions().add("walking",
					new Walking(character.getActions(), actions.walking));
		else
			throw new RuntimeException("Walking action undefined for player");
	}

}
