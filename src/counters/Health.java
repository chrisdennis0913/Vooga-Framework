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
		int fullSize = 480;
		int curSize = getCount()/getInitial()*480;
		int height = 15;
		
		Point loc = new Point(10, 10);
		
		Rectangle bg = new Rectangle(loc, new Dimension(fullSize, height));
		Rectangle fg = new Rectangle(loc, new Dimension(curSize, height));
		
		g.setColor(new Color(0));
		g.draw(bg);
		g.fill(bg);
		
		g.setColor(new Color(255, 0, 0));
		g.draw(fg);
		g.fill(fg);
	}

}
