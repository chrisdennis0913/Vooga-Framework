package testing;

import gameCharacter.GameCharacter;

import java.awt.Dimension;
import java.awt.Graphics2D;

import utils.Location;


import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class GameCharacterTest extends Game {

	GameCharacter player;

	public void initResources() {
		Location loc = new Location(120, 80);
		String config = "rsc/config/player_directions.json";
		player = new GameCharacter(this, loc, config);
	}

	public void render(Graphics2D g) {
		player.render(g);
	}

	public void update(long elapsed) {
		player.update(elapsed);
	}

	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new GameCharacterTest(), new Dimension(480, 320), false);
		game.start();
	}

}
