package player;

import utils.JsonUtil.JSONPlayerTalking;
import utils.KeyHandle;
import actions.ActionDecorator;
import actions.Talk;

public class Talking extends ActionDecorator{
	
	private static final long serialVersionUID = 1L;
	private KeyHandle keys;

	public Talking(Talk talk) {
		super(talk);
		initResources();
	}
	
	public void initResources(){
		keys = new KeyHandle(getWrapper().getCharacter().getGame());
		
		JSONPlayerTalking talking = (JSONPlayerTalking) getJsonable();
		if (talking.keys == null)
			new RuntimeException("Talking keys undefined");
		
		keys.add(Talk.TALK_BASIC, talking.keys);
	}

}
