package demoGame;

import java.awt.Dimension;
import app.RPGame;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class DemoMain extends GameEngine{

    public static final int GAME_MODE = 1;
    public String configURL;
    
    public DemoMain(String configURL) {
        this.configURL = configURL;
    }
    
    public void initResources() {
        nextGameID = GAME_MODE;
    }
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new DemoMain("demoGame/config/demoGame.json"), new Dimension(640, 480), false);
        game.start();
    }
    public GameObject getGame(int GameID) {
        switch (GameID) {
            case GAME_MODE : return new RPGame(this, configURL);
        }
        return null;
    }

}
