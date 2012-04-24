package player;

import utils.JsonUtil;
import utils.KeyHandle;
import actions.Action;
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
		
		JsonUtil.JSONPlayerAttacking attacking = (JsonUtil.JSONPlayerAttacking) getJsonable();
		if (attacking.keys == null)
			new RuntimeException("Attack keys undefined");
	}

}
