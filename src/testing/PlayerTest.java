package testing;

import gameCharacter.GameCharacter;

import java.awt.Dimension;
import java.awt.Graphics2D;

import player.Player;
import utils.Location;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class PlayerTest extends Game {

	Player player;

	public void initResources() {
		Location loc = new Location(120, 80);
		String config = "rsc/config/player_directions.json";
		String actions = "rsc/config/player_actions.json";
		player = new Player(new GameCharacter(this, loc, config), actions) ;
	}

	public void render(Graphics2D g) {
		player.render(g);
	}

	public void update(long elapsed) {
		player.update(elapsed);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new PlayerTest(), new Dimension(480, 320), false);
		game.start();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	}

}
