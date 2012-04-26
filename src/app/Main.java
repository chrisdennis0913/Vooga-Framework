package app;

import java.awt.Dimension;

import level.LevelEditor;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;


public class Main extends GameEngine {
	
	public static final int GAME_MODE = 1, LEVEL_EDITOR = 2;
	
	public void initResources() {
		//nextGameID = GAME_MODE;
		nextGameID = LEVEL_EDITOR;
	}
	
	public GameObject getGame(int GameID) {
		switch (GameID) {
			case GAME_MODE : return new RPGame(this);
			case LEVEL_EDITOR : return new LevelEditor(this);
		}
		return null;
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Main(), new Dimension(640, 480), false);
		game.start();
	}
}
