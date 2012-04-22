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
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.google.gson.Gson;


public class RPGame extends GameObject {
    private PlayField field;
    //private Background bg;
    private Player player;
    //private Dialog dialog;
    private Level level;
    private Inventory myInventory;
    String levelFileName;
    String lower, upper;

    public RPGame (GameEngine parent) {
        super(parent);
    }


    public void initResources () {
    	level = new Level(bsLoader, bsIO, this, levelFileName, lower, upper);
    	field = new PlayField(level);
    	field.setComparator(new Comparator() {
			public int compare(Object o1, Object o2) {
				// sort based on y-order
				return (int) (((Sprite) o1).getY()-((Sprite) o2).getY());
			}
		} );
    	
    	//parse JSON files and set up player and npcs
    	Gson gson = new Gson();
    		JsonUtil.JSONLevel peeps = gson.fromJson(json, JsonUtil.JSONLevel.class);
    	
    	
    }


    public void render (Graphics2D g) {}


    public void update (long elapsed) {}


    public Player getPlayer () {
        return player;
    }
    
    public void addItems(Item itm){
        myInventory.add(itm);
    }

}
