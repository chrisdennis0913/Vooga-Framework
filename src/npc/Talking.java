package npc;

import utils.KeyHandle;
import actions.Action;
import actions.ActionDecorator;
import actions.Talk;

public class Talking extends ActionDecorator{
	
	/**
	 * computer-generated serial version ID number
	 */
	private static final long serialVersionUID = 1873153600480336626L;
	KeyHandle keys;

	public Talking(Talk talk) {
		super(talk);
		// TODO Auto-generated constructor stub
	}
	
	public void initResources(){
		keys = new KeyHandle(getWrapper().getCharacter().getGame());

	}

}
