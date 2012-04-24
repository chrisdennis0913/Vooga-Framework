package app;

import inventory.Inventory;
import inventory.Item;

import java.awt.Graphics2D;
import java.util.Comparator;

import level.Level;
import player.Player;
import utils.JsonUtil;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;

public class RPGame extends GameObject {

	private final String gameURL = "rsc/config/game.json";

	private PlayField field = new PlayField();

	// private Background bg;
	private Player player;
	// private Dialog dialog;
	private Level level;
	private Inventory myInventory;
	String levelFileName = "rsc/savedmaps/level1.json";
	String lower, upper;
	boolean pausedForInventory = false;

	public RPGame(GameEngine parent) {
		super(parent);
	}

	public void initResources() {
		Gson gson = new Gson();
		JsonUtil.JSONGame gameJson = gson.fromJson(JsonUtil.getJSON(gameURL),
				JsonUtil.JSONGame.class);

		level = new Level(bsLoader, bsIO, this, gameJson.level);
		field.setComparator(new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return (int) (((Sprite) o1).getY() - ((Sprite) o2).getY());
			}
		});
	}

	public void render(Graphics2D g) {
		field.render(g);
		level.render(g);
		getPlayer().render(g); 			// this is a hack that should not be there!
	}

	public void update(long elapsed) {
		field.update(elapsed);
		level.update(elapsed);
		getPlayer().update(elapsed);	// this is a hack that should not be there!
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		field.add(player);
	}

	public void addItems(Item itm) {
		myInventory.add(itm);
	}

	public PlayField getField() {
		return field;
	}

	public void pauseGameForInventory() {
		pausedForInventory = true;
	}

	public void unPauseGameForInventory() {
		pausedForInventory = false;
	}

}