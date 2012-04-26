package level;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import player.Player;
import quest.QuestJournal;

import utils.JsonUtil;

import level.Level;
import app.RPGame;

import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.util.*;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

public class LevelEditor extends Game{


	/**
	 * Arrow key	: navigate
	 * Space		: switch lower/upper tile
	 * Page down	: next tile
	 * Page up		: prev tile
	 * End			: fast next tile
	 * Home			: fast prev tile
	 * Right click	: select tile
	 * Click		: put tile
	 * Ctrl + S		: save
	 */

	private final String gameURL = "rsc/config/game.json";

	public PlayField field = new PlayField();

	private Player player;
	public Level level;
	private QuestJournal myJournal;
	String lower, upper;
	boolean pausedForInventory = false;
	RPGame game;
	
	int 	tilenum;
	int		tilemode;
	
	public void initResources() {
		Gson gson = new Gson();
		JsonUtil.JSONGame gameJson = gson.fromJson(JsonUtil.getJSON(gameURL),
				JsonUtil.JSONGame.class);

		level = new Level(bsLoader, bsIO, game, gameJson.level);
	}


	public void update(long elapsedTime) {
		level.update(elapsedTime);

		// navigate
		if (keyDown(KeyEvent.VK_LEFT)) {
			level.move(-0.2*elapsedTime, 0);
		}
		if (keyDown(KeyEvent.VK_RIGHT)) {
			level.move(0.2*elapsedTime, 0);
		}
		if (keyDown(KeyEvent.VK_UP)) {
			level.move(0, -0.2*elapsedTime);
		}
		if (keyDown(KeyEvent.VK_DOWN)) {
			level.move(0, 0.2*elapsedTime);
		}

		// switch lower/upper tile
		if (keyPressed(KeyEvent.VK_SPACE)) {
			if (++tilemode > 2) 
				tilemode = 0;
			else
				tilemode += 1;

			// validate current mode tile count
			if (tilenum > getChipsetLength()) {
				tilenum = getChipsetLength();
			}
		}

		// next/prev tile
		if (keyPressed(KeyEvent.VK_PAGE_DOWN) || keyDown(KeyEvent.VK_END)) {
			if (++tilenum > getChipsetLength()) {
				tilenum = getChipsetLength();
			}
		}
		if (keyPressed(KeyEvent.VK_PAGE_UP) || keyDown(KeyEvent.VK_HOME)) {
			if (--tilenum < 0) {
				tilenum = 0;
			}
		}


		Point tileAt = level.getTileAt(getMouseX(), getMouseY());
		if (tileAt != null) {
			// put tile
			if (bsInput.isMouseDown(MouseEvent.BUTTON1)) {
				if(tilemode == 2) {
					//swing code to take attributes of sprite
					
					//save sprite
					
				}
				else
					getLayer() [tileAt.x] [tileAt.y] = tilenum;
			}


			// select tile
			if (rightClick()) {
				tilenum = getLayer() [tileAt.x] [tileAt.y];
			}
		}


		// save to file with name map00.lwr and map00.upr
		if (keyPressed(KeyEvent.VK_S) && keyDown(KeyEvent.VK_CONTROL)) {
			String[] lowerTile = new String[level.layer1[0].length];
			String[] upperTile = new String[level.layer1[0].length];
			for (int i=0;i < level.layer1.length;i++)
				for (int j=0;j < level.layer1[0].length;j++) {
					if (lowerTile[j] == null) lowerTile[j] = "";
					lowerTile[j] += String.valueOf(level.layer1[i][j])+" ";

					if (upperTile[j] == null) upperTile[j] = "";
					upperTile[j] += String.valueOf(level.layer2[i][j])+" ";
				}
			FileUtil.fileWrite(lowerTile, bsIO.setFile("map00.lwr"));
			FileUtil.fileWrite(upperTile, bsIO.setFile("map00.upr"));
		}
	}


	//		private int getTileX() {
	//			// convert mouse x coordinate to map tile
	//			return (int) ((map.getX() + getMouseX()) / 32);
	//		}
	//
	//		private int getTileY() {
	//			// convert mouse y coordinate to map tile
	//			return (int) ((map.getY() + getMouseY()) / 32);
	//		}
	//
	// since lower tile has additional different tile
	// the length of lower tile is chipsetE + additional tile
	private int getChipsetLength() {
		switch (tilemode) {
		case 0: return level.chipsetE.image.length + level.chipset.length - 2;	// lower mode
		case 1: return level.chipsetF.image.length - 1;	// upper mode
		case 2: return level.chipsetG.image.length -1; // sprite mode
		}
		return 0;
	}

	private BufferedImage getChipsetImage(int num) {
		if (num == -1) {
			return null;
		}

		switch (tilemode) {
		// lower mode
		case 0:
			if (num < level.chipsetE.image.length) {
				return level.chipsetE.image[num];
			} else {
				return level.chipset[num-level.chipsetE.image.length].image[2];
			}

		// upper mode
		case 1:
			return level.chipsetF.image[num];
			
		// sprite mode - return chipset array
		case 2:
			return level.chipsetG.image[num];
		}

		return null;
	}


	// get tiles
	private int[][] getLayer() {
		switch (tilemode) {
		case 0: return level.layer1;	// lower mode
		case 1: return level.layer2;	// upper mode
		case 2: return level.layer2;	// sprite mode
		}

		return null;
	}


	public void render(Graphics2D g) {
		level.render(g);

		// selected tile
		if (getChipsetImage(tilenum) != null) {
			g.drawImage(getChipsetImage(tilenum), 600, 40, null);
		}
		g.setColor(Color.BLACK);
		g.drawRect(600, 40, 32, 32);

		Point tileAt = level.getTileAt(getMouseX(), getMouseY());
		if (tileAt != null) {
			g.setColor(Color.WHITE);
			int posX = (tileAt.x - level.getTileX()) * 32,
			posY = (tileAt.y-level.getTileY()) * 32;
			g.drawRect(posX - level.getOffsetX() + level.getClip().x,
					posY - level.getOffsetY() + level.getClip().y,
					32, 32);
		}
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new LevelEditor(), new Dimension(640,480), false);
		game.start();
	}

}
