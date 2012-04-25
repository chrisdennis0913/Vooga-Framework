package actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.font.SystemFont;

import utils.Jsonable;
import evented.EventedWrapper;

public class Talk extends Action {

	private static final long serialVersionUID = 1L;

	private String script = "";
	
	public static final int TALK_BASIC = 10;

	public Talk(EventedWrapper<ActionInterface> wrapper, Jsonable json) {
		super(wrapper, json);
		initResources();
	}

	public void initResources() {
		setEnabled(false, true);
	}

	public void update(long elapsed) {}

	public void render(Graphics2D g) {
		if (isActive())
			drawDialogBox(g);
	}

	public void setActiveDirection(int direction) {
	}

	private void drawDialogBox(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12),
				new Color(255, 255, 255));
		g.setColor(new Color(0));
		g.drawRect(10, 340, 380, 50);
		g.fillRect(10, 340, 380, 50);
		font.drawText(g, script, SystemFont.LEFT, 15, 345, 330, 2, 0);
	}

	public void set(String message) {
		setActive(true);
		this.script = message;
	}

}
