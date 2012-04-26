package counters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import evented.EventedWrapper;

public class Health extends Counter {

	private static final long serialVersionUID = 1L;

	public Health(EventedWrapper<Counter> wrapper, int count) {
		super(wrapper, count);
	}

	public void initResources() {}

	public void update(long elapsed) {}

	public void render(Graphics2D g) {
		drawMeter(g);
	}
	
	public void drawMeter(Graphics2D g) {
		int bgWidth = 460;
		int fgWidth = getCount()/getInitial()*bgWidth;
		int bgHeight = 15;
		int fgHeight = bgHeight - 2;
		
		Point bgloc = new Point(10, 10);
		Point fgloc = new Point((int) bgloc.getX() + 1, (int) bgloc.getY() + 1);
		
		Rectangle bg = new Rectangle(bgloc, new Dimension(bgWidth, bgHeight));
		Rectangle fg = new Rectangle(fgloc, new Dimension(fgWidth, fgHeight));
		
		g.setColor(new Color(0));
		g.draw(bg);
		g.fill(bg);
		
		g.setColor(new Color(255, 0, 0));
		g.draw(fg);
		g.fill(fg);
	}

}
