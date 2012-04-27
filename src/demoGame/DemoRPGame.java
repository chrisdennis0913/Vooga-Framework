package demoGame;

import level.Level;
import utils.JsonUtil;
import app.RPGame;
import com.golden.gamedev.GameEngine;
import com.google.gson.JsonObject;

public class DemoRPGame extends RPGame {
    
    public DemoRPGame (GameEngine parent, String configURL) {
        super(parent, configURL);
        JsonObject gameJson = JsonUtil.getJSON(gameURL);
        level = new Level(bsLoader, bsIO, this, gameJson.get("level").getAsString());
    }

}
