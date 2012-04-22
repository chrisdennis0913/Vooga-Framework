package gameCharacter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import actions.Action;
import app.Location;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.FileUtil;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;

import counters.Counter;
import evented.EventedWrapper;

/**
 * Basic class for all character game objects: enemies, automated characters,
 * and players
 * 
 * Allows the developer to load directions from a JSON file (see Direction for
 * more info).
 * 
 * Contains a common current direction to be shared between the actions.
 * 
 * Contains Actions and Counters wrappers to execute the actions
 * 
 * @author Kirill Klimuk
 */

public class GameCharacter extends AnimatedSprite implements CharacterInterface {

	private static final long serialVersionUID = 1L;

	private Game game;

	private int curDirection = 0;
	private List<Direction> directions;

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
		String json = loadJSON(configURL);
		constructDirections(json);
		stop();
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		counters.render(g);
		actions.render(g);
	}

	public void update(long elapsed) {
		super.update(elapsed);
		counters.update(elapsed);
		actions.update(elapsed);
	}

	private String loadJSON(String configURL) {
		String[] jsonPacked = FileUtil.fileRead(new File(configURL));

		StringBuilder jsonBuilder = new StringBuilder();
		for (String line : jsonPacked) {
			jsonBuilder.append(line);
		}

		return jsonBuilder.toString();
	}

	private void constructDirections(String json) {
		Gson gson = new Gson();
		JSONDirections dirs = gson.fromJson(json, JSONDirections.class);

		Direction[] tempDirections = new Direction[4];

		for (JSONDirection direction : dirs.directions) {
			BufferedImage image = game.getImage(direction.image);
			BufferedImage[] images = ImageUtil.splitImages(image, dirs.frames, 1);

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

	private class JSONDirections {
		public int frames;
		public int delay;
		public JSONDirection[] directions;
	}
	
	private class JSONDirection {
		public String direction;
		public String image;
	}

	public boolean isCurrentDirection(int direction) {
		return direction == curDirection;
	}

	public Direction getCurrentDirection() {
		return directions.get(curDirection);
	}

	public void setCurrentDirection(int direction, boolean animate) {
		curDirection = direction;
		directions.get(direction).changeCharacter(animate);
	}
	
	public void stop() {
		directions.get(curDirection).changeCharacter(false);
	}
}
