import java.awt.Graphics2D;

import com.golden.gamedev.object.AnimatedSprite;


public abstract class GameCharacter extends AnimatedSprite{
	
	private int currDirection = 0;
	
	private final static int DIR_DOWN = 0;
	private final static int DIR_UP = 1;
	private final static int DIR_LEFT = 2;
	private final static int DIR_RIGHT = 3;
	
	public GameCharacter(){
		initResources();
	}
	
	protected abstract void initResources(long elapsed);
	public abstract void render(Graphics2D g);
	protected abstract void update();
	//JSON direction, counters, actions
	
	//game as singleton
	//initResources, render, update

}
