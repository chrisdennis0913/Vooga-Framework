package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import counters.Counter;

public class Meter {

	private Counter counter;

	public Meter(Counter counter) {
		this.counter = counter;
	}

	private Dimension getMeterSize(Dimension size) {
		int fgWidth = (int) ((double) counter.getCount() / counter.getInitial() * size.getWidth()) - 4;
		int fgHeight = (int) (size.getHeight() - 4);
		
		return new Dimension(fgWidth, fgHeight);
	}

	public void show(Graphics2D g, Location loc, Dimension size, Color color) {
		Point bgloc = new Point(loc.getX(), loc.getY());
		Point fgloc = new Point((int) bgloc.getX() + 2, (int) bgloc.getY() + 2);
		
		Rectangle bg = new Rectangle(bgloc, size);
		Rectangle fg = new Rectangle(fgloc, getMeterSize(size));
		
		g.setColor(new Color(0));
		g.draw(bg);
		g.fill(bg);

		g.setColor(color);
		g.draw(fg);
		g.fill(fg);
	}

}
