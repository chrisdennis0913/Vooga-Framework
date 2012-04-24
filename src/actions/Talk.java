package actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.object.font.SystemFont;

import utils.Jsonable;
import evented.EventedWrapper;

public class Talk extends Action{
	
	public String message;

	public Talk(EventedWrapper<ActionInterface> wrapper, Jsonable json) {
		super(wrapper, json);
		initResources();
	}
	
	public void initResources(){
		setEnabled(true, false);
	}
	
	public void update(long elapsed){
	}

	public void render(Graphics2D g) {
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255,255,255));
		drawDialogBox(g);
		font.drawText(g, message, SystemFont.LEFT, 15, 345, 330, 2, 0);
	}

	public void setActiveDirection(int direction) {
	}
	
	private void drawDialogBox(Graphics2D g) {
		g.setColor(new Color(0));
		g.drawRect(10, 340, 380, 50);
		g.fillRect(10, 340, 380, 50);
	}

}
