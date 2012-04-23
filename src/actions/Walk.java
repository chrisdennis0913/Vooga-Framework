package actions;

import java.awt.Graphics2D;

import utils.Jsonable;
import evented.EventedWrapper;

public class Walk extends Action {

	private static final long serialVersionUID = 1L;

	public Walk(EventedWrapper<ActionInterface> eventedWrapper, Jsonable json) {
		super(eventedWrapper, json);
		initResources();
	}
	
	public void initResources() {
		setEnabled(true, false);
	}

	public void update(long elapsed) {
	}

	public void render(Graphics2D g) {
	}

	public void setActiveDirection(int direction) {
	}

}
