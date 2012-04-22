package gameCharacter;

import inventory.Inventory;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import utils.Direction;
import utils.JsonUtil;
import utils.Location;
import utils.Speed;

import actions.Action;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;

import counters.Counter;
import evented.EventedWrapper;

/**
 * Basic class for all character game objects: enemies, automated characters,
 * and players.
 * 
 * Allows the developer to load directions from a JSON file (see Direction for
 * more info).
 * 
 * Contains a common current direction to be shared between the actions.
 * 
 * Contains Actions and Counters wrappers to execute the actions.
 * 
 * @author Kirill Klimuk
 */

public class GameCharacter extends AnimatedSprite implements CharacterInterface {

	private static final long serialVersionUID = 1L;

	private Game game;

	private int curDirection = 0;
	private List<Direction> directions;
	private Speed speed = new Speed(0);
	private Inventory inventory;
	private String configURL;

	private EventedWrapper<Counter> counters = new EventedWrapper<Counter>(this);
	private EventedWrapper<Action> actions = new EventedWrapper<Action>(this);

	public static final int DIR_DOWN = 0;
	public static final int DIR_UP = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;

	public GameCharacter(Game game, Location loc, String configURL) {
		super(loc.getX(), loc.getY());
		this.game = game;
		this.configURL = configURL;
		initResources();
	}

	public void initResources() {
		String json = JsonUtil.getJSON(configURL);
		constructDirections(json);
		stop();
		inventory = new Inventory();
	}

	public void render(Graphics2D g) {	
		super.render(g);
		counters.render(g);
		actions.render(g);
	}
		
	public void update(long elapsed) {
		double[] curSpeed = speed.get(getCurrentDirection());
		setSpeed(curSpeed[0], curSpeed[1]);
		super.update(elapsed);
		counters.update(elapsed);
		actions.update(elapsed);
	}

	public void setSpeed(double speed) {
		this.speed.set(speed);
	}

	public Game getGame() {
		return game;
	}
	
	private void constructDirections(String json) {
		Gson gson = new Gson();
		JsonUtil.JSONDirections dirs = gson.fromJson(json,
				JsonUtil.JSONDirections.class);

		Direction[] tempDirections = new Direction[4];

		for (JsonUtil.JSONDirection direction : dirs.directions) {
			BufferedImage image = game.getImage(direction.image);
			BufferedImage[] images = ImageUtil.splitImages(image, dirs.frames,
					1);

			int intepretedDirection = 0;

			if (direction.direction.equals("DIR_DOWN"))
				intepretedDirection = GameCharacter.DIR_DOWN;
			else if (direction.direction.equals("DIR_UP"))
				intepretedDirection = GameCharacter.DIR_UP;
			else if (direction.direction.equals("DIR_LEFT"))
				intepretedDirection = GameCharacter.DIR_LEFT;
			else if (direction.direction.equals("DIR_RIGHT"))
				intepretedDirection = GameCharacter.DIR_RIGHT;
			else
				throw new RuntimeException("Invalid direction specified");

			tempDirections[intepretedDirection] = new Direction(this, images,
					intepretedDirection, dirs.delay);
		}

		directions = Arrays.asList(tempDirections);
	}

	public EventedWrapper<Action> getActions() {
		return actions;
	}
	
	public EventedWrapper<Counter> getCounters() {
		return counters;
	}
	
	public boolean isCurrentDirection(int direction) {
		return direction == curDirection;
	}

	public int getCurrentDirection() {
		return curDirection;
	}

	public void setActiveDirection(int direction) {
		curDirection = direction;
		directions.get(direction).changeCharacter(true);
	}

	public void stop() {
		speed.set(0);
		directions.get(curDirection).changeCharacter(false);
	}
	
	public Inventory getInventory(){
	    return inventory;
	}
	
}
