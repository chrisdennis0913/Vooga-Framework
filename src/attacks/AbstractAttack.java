package attacks;

import gameCharacter.GameCharacter;

import com.golden.gamedev.object.Timer;

import app.RPGame;

/**
 * Defines a standard attack between two GameCharacters.
 * 
 * @author jameshong
 *
 */
public abstract class AbstractAttack {
	protected RPGame game;
	protected GameCharacter attacker;
	protected GameCharacter target;
	private String name;
	private Timer timer;
	protected boolean active = true;
	
	public AbstractAttack (RPGame game, GameCharacter attacker, String name){
		this.game = game;
		this.attacker = attacker;
		this.name = name;
	}
	
	public AbstractAttack (RPGame game, GameCharacter attacker, GameCharacter target, String name){
		this(game, attacker, name);
		setTarget(target);
	}

	public void setTarget(GameCharacter target){
		this.target = target;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void setTimer(int timeInterval){
		timer = new Timer(timeInterval);
	}

	public boolean isTimerTriggered(long elapsedTime){
		return timer.action(elapsedTime);
	}
	
	public double getAttackerX(){
		return attacker.getX();
	}

	public double getAttackerY(){
		return attacker.getY();
	}

	public String getName(){
		return name;
	}
	
	public abstract boolean isAvailable(long elapsedTime);
	
	public abstract void performAttack(long elapsedTime);
		
	public abstract int calculateDamage(long elapsedTime);

}
