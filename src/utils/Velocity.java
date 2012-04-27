package utils;

import gameCharacter.GameCharacter;

/**
 * Common class for retrieving velocities                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
 * 
 * @author Kirill Klimuk
 */

public class Velocity {
	
	private double speed; 
	
	public Velocity(double speed) {
		this.speed = speed;
	}
	
	public void set(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public double[] get(int direction) {
		if (direction == GameCharacter.DIR_DOWN)
			return new double[]{0, speed};
		else if (direction == GameCharacter.DIR_UP)
			return new double[]{0, -speed};
		else if (direction == GameCharacter.DIR_LEFT)
			return new double[]{-speed, 0};
		else
			return new double[]{speed, 0};
	}
	
	public String toString() {
		return "[speed: "+speed+"]";
	}
}
