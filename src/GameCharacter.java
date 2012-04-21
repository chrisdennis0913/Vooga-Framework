import com.golden.gamedev.object.AnimatedSprite;


public abstract class GameCharacter extends AnimatedSprite{
	
	private int currDirection = 0;
	
	private final static int DIR_DOWN = 0;
	private final static int DIR_UP = 1;
	private final static int DIR_LEFT = 2;
	private final static int DIR_RIGHT = 3;

}
