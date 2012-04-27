package testing;

import java.awt.Dimension;

import app.Main;

import com.golden.gamedev.GameLoader;

public class PlayerTest {
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Main("rsc/config/game.json"), new Dimension(480, 320), false);
		game.start();
	}
}
