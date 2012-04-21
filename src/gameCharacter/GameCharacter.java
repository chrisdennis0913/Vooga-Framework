package gameCharacter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.Location;
import app.RPGame;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.FileUtil;
import com.google.gson.Gson;


public abstract class GameCharacter extends AnimatedSprite{
	
	private RPGame game;
	
	private int curDirection = 0;
	
	private final static int DIR_DOWN = 0;
	private final static int DIR_UP = 1;
	private final static int DIR_LEFT = 2;
	private final static int DIR_RIGHT = 3;
	
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
		JSONDirection[] directions = gson.fromJson(json, JSONDirection[].class);

		for (JSONDirection direction : directions) {
			BufferedImage[] images = new BufferedImage[direction.images.length];
			for (int i = 0; i < images.length; i++)
				images[i] = player.getGame().getImage(direction.images[i]);

			List<Integer> keys = new ArrayList<Integer>();
			for (int i = 0; i < direction.keys.length; i++)
				keys.add(direction.keys[i]);

			getDirections()[direction.cardinal] = new Direction(this,
					images);
		}
	}
	
	private class JSONDirection {
		public int direction;
		public String[] images;
	}
	
	public abstract void render(Graphics2D g);
	
	protected abstract void update();
	//JSON direction, counters, actions
	
	//game as singleton
	//initResources, render, update

}
