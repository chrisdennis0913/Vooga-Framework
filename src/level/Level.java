package level;

import inventory.ConcreteItem;
import inventory.Item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

import npc.NPC;
import player.Player;
import utils.JsonUtil;
import utils.JsonUtil.JSONInventory;
import utils.JsonUtil.JSONItem;
import utils.JsonUtil.JSONNpc;
import utils.JsonUtil.JSONPlayer;
import utils.Location;
import app.RPGame;
import collisions.ItemCollision;
import collisions.NPCCollision;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.engine.timer.SystemTimer;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.abstraction.AbstractTileBackground;
import com.golden.gamedev.util.FileUtil;
import com.golden.gamedev.util.ImageUtil;
import com.google.gson.Gson;

import evented.Evented;
import gameCharacter.GameCharacter;

public class Level extends AbstractTileBackground implements Evented {

	private static final long serialVersionUID = 1L;

	private final String levelname;
	private BaseIO baseio;
	private BaseLoader bsloader;

	private Chipset chipsetE;
	private Chipset chipsetF;
	private Chipset[] chipset;

	private static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	int[][] layer1 = new int[40][25]; // the lower tiles
	int[][] layer2 = new int[40][25]; // the fringe tiles

	private SystemTimer levelTimer = new SystemTimer();
	protected long levelStartTime;
	protected String nextLevelName;
	protected String startText;

	private RPGame game;
	private LevelInventory<Item> inventory;

	public Level(BaseLoader bsLoader, BaseIO bsIO, RPGame game, String levelname) {
		super(0, 0, TILE_WIDTH, TILE_HEIGHT);

		this.game = game;
		this.inventory = new LevelInventory<Item>(game);
		this.levelname = levelname;
		this.baseio = bsIO;
		this.bsloader = bsLoader;

		initResources();
	}

	public void initResources() {
		Gson gson = new Gson();
		String json = JsonUtil.getJSON(levelname);

		JsonUtil.JSONLevel level = gson
				.fromJson(json, JsonUtil.JSONLevel.class);

		setPlayer(level);
		setNpcs(level);
		setItems(level);

		setCollisions();

		setTiles(level);
		setSize(layer1.length, layer1[0].length);
		setChipsets();
		setLevelTimer();
	}

	private void setCollisions() {
		ItemCollision itCol = new ItemCollision();
		NPCCollision collision = new NPCCollision();
		
		game.getField().addCollisionGroup(game.getField().getGroup("player"),
				game.getField().getGroup("npcs"), collision);
		game.getField().addCollisionGroup(game.getField().getGroup("player"),
				game.getField().getGroup("items"), itCol);
	}

	private void setChipsets() {
		chipsetE = new Chipset(bsloader.getImages("rsc/level/ChipSet2.png", 6,
				24, false));
		chipsetF = new Chipset(bsloader.getImages("rsc/level/ChipSet3.png", 6,
				24));

		chipset = new Chipset[16];
		BufferedImage[] image = bsloader.getImages("rsc/level/ChipSet1.png", 4,
				4, false);
		int[] chipnum = new int[] { 0, 1, 4, 5, 8, 9, 11, 12, 2, 3, 6, 7, 10,
				11, 14, 15 };
		for (int i = 0; i < chipset.length; i++) {
			int num = chipnum[i];
			BufferedImage[] chips = ImageUtil.splitImages(image[num], 3, 4);
			chipset[i] = new Chipset(chips);
		}
	}

	public LevelInventory<Item> getInventory() {
		return inventory;
	}

	private void setLevelTimer() {
		levelTimer.setFPS(100);
		levelTimer.startTimer();
		levelStartTime = levelTimer.getTime();
	}

	private void setPlayer(JsonUtil.JSONLevel level) {
		JSONPlayer jPlayer = level.player;
		SpriteGroup group = new SpriteGroup("player");

		Location playerLoc = new Location(jPlayer.location);
		Player player = new Player(new GameCharacter(game, playerLoc,
				jPlayer.directionsURL), jPlayer.actionsURL);

		game.setPlayer(player);
		group.add(player.getCharacter());
		game.getField().addGroup(group);
	}

	private void setItems(JsonUtil.JSONLevel level) {
		JSONInventory inventory = level.inventory;
		SpriteGroup group = new SpriteGroup("items");

		for (JSONItem it : inventory.items) {
			Item item = new ConcreteItem(game, it);
			group.add(item);
		}

		game.getField().addGroup(group);
	}

	private void setNpcs(JsonUtil.JSONLevel level) {
		JSONNpc[] npcs = level.npcs;
		SpriteGroup group = new SpriteGroup("npcs");

		for (JSONNpc jsonNpc : npcs) {
			Location loc = new Location(jsonNpc.location);
			NPC npc = new NPC(game, loc, jsonNpc.directions);
			group.add(npc);
		}
		game.getField().addGroup(group);
	}

	private void setTiles(JsonUtil.JSONLevel level) {
		String[] lowerTile = FileUtil.fileRead(baseio
				.getStream(level.lowerFilename));
		String[] upperTile = FileUtil.fileRead(baseio
				.getStream(level.upperFilename));
		for (int j = 0; j < layer1[0].length; j++) {
			StringTokenizer lowerToken = new StringTokenizer(lowerTile[j]);
			StringTokenizer upperToken = new StringTokenizer(upperTile[j]);
			for (int i = 0; i < layer1.length; i++) {
				layer1[i][j] = Integer.parseInt(lowerToken.nextToken());
				layer2[i][j] = Integer.parseInt(upperToken.nextToken());
			}
		}
	}

	public void nextLevel(String next) {

	}

	public void setStartText(String text) {
		startText = text;
	}

	public void setNextLevel(String nextLevel) {
		nextLevelName = nextLevel;
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < layer1.length; i++) {
			for (int j = 0; j < layer1[0].length; j++) {
				renderTile(g, i, j, 32 * i, 32 * j);
			}
		}
		
		GameCharacter player = game.getPlayer().getCharacter();
		setToCenter((int) player.getX(),
				(int) player.getY(), player.getWidth(), player.getHeight());

		super.render(g);
	}

	public void renderTile(Graphics2D g, int tileX, int tileY, int x, int y) {
		// render layer 1
		int tilenum = layer1[tileX][tileY];

		if (tilenum < chipsetE.image.length)
			g.drawImage(chipsetE.image[tilenum], x, y, null);
		else if (tilenum >= chipsetE.image.length) {
			BufferedImage image = chipset[tilenum - chipsetE.image.length].image[2];
			g.drawImage(image, x, y, null);
		}

		// render layer 2
		int tilenum2 = layer2[tileX][tileY];
		if (tilenum2 != -1) {
			g.drawImage(chipsetF.image[tilenum2], x, y, null);
		}
	}

	public boolean isOccupied(int tileX, int tileY) {
		try {
			return (layer2[tileX][tileY] != -1);
		} catch (Exception e) {
			// out of bounds
			return true;
		}
	}

	// chipset is only a pack of images
	class Chipset {
		BufferedImage[] image;

		public Chipset(BufferedImage[] image) {
			this.image = image;
		}

	}
}