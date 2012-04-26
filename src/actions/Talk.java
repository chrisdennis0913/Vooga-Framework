package actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import npc.NPC;

import com.golden.gamedev.object.font.SystemFont;
import com.google.gson.JsonObject;

import evented.EventedWrapper;

public class Talk extends Action {

	private static final long serialVersionUID = 1L;

	private String script = "";
	private NPC npc;
	
	public static final int TALK_BASIC = 10;

	public Talk(EventedWrapper<ActionInterface> wrapper, JsonObject json) {
		super(wrapper, json);
		initResources();
	}

	public void initResources() {
		setEnabled(false, true);
	}

	public NPC getNpc() {
		return npc;
	}

	public void setNpc(NPC npc) {
		this.npc = npc;
	}

	public void update(long elapsed) {}

	public void render(Graphics2D g) {
		if (isActive())
			drawDialogBox(g);
	}

	private void drawDialogBox(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12),
				new Color(255, 255, 255));
		g.setColor(new Color(0));
		g.drawRect(10, 260, 460, 50);
		g.fillRect(10, 260, 460, 50);
		font.drawText(g, script, SystemFont.LEFT, 15, 265, 260, 2, 0);
	}

	public void setMessage(String message) {
		setActive(true);
		this.script = message;
	}

	public void setActiveDirection(int direction) {}

}
