package utils;

import gameCharacter.GameCharacter;

/**
 * Common class for retrieving speeds in different directions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
 * 
 * @author Kirill Klimuk
 */

public class Speed {
	
	private double x;
	private double y;
	
	public Speed(double speed) {
		this.x = speed;
		this.y = speed;
	}
	
	private double getX() {
		return x;
	}
	
	private double getY() {
		return y;
	}
	
	public void set(double speed) {
		this.x = speed;
		this.y = speed;
	}
	
	public double[] get(int direction) {
		if (direction == GameCharacter.DIR_DOWN)
			return new double[]{0, getX()};
		else if (direction == GameCharacter.DIR_UP)
			return new double[]{0, -getX()};
		else if (direction == GameCharacter.DIR_LEFT)
			return new double[]{-getY(), 0};
		else
			return new double[]{getY(), 0};
	}
}
