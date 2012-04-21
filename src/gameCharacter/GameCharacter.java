package gameCharacter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import app.Location;
import app.RPGame;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.FileUtil;
import com.google.gson.Gson;

public abstract class GameCharacter extends AnimatedSprite {
	
	private static final long serialVersionUID = 1L;

	private RPGame game;

	private int curDirection = 0;
	private List<Direction> directions;

	public static final int DIR_DOWN = 0;
	public static final int DIR_UP = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;

	public GameCharacter(RPGame game, Location loc, String configURL) {
		super(loc.getX(), loc.getY());
		this.game = game;
		initResources(configURL);
	}

	private void initResources(String configURL) {
		String json = loadJSON(configURL);
		constructDirections(json);
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
		JSONDirection[] dirs = gson.fromJson(json, JSONDirection[].class);

		Direction[] tempDirections = new Direction[4];

		for (JSONDirection direction : dirs) {
			BufferedImage[] images = new BufferedImage[direction.images.length];
			for (int i = 0; i < images.length; i++)
				images[i] = game.getImage(direction.images[i]);

			tempDirections[direction.direction] = new Direction(this, images,
					direction.direction, direction.delay);
		}

		directions = Arrays.asList(tempDirections);
	}

	private class JSONDirection {
		public int direction;
		public int delay;
		public String[] images;
	}

	public abstract void render(Graphics2D g);

	protected abstract void update();

	public boolean isCurrentDirection(int direction) {
		return direction == curDirection;
	}
	
	public Direction getCurrentDirection() {
		return directions.get(curDirection);
	}

	public void setDirection(int direction, boolean animate) {
		this.curDirection = direction;
		directions.get(direction).changeCharacter(animate);
	}
}
