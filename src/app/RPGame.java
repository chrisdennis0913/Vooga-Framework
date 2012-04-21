package app;
import inventory.Inventory;
import java.awt.Graphics2D;
import level.Level;
import level.Map;
import player.Player;
import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.Background;


public class RPGame extends GameObject {
    private PlayField field;
    public Map map;
    private Background bg;
    private Player player;
    private Dialog dialog;
    private Level level;
    private Inventory myInventory;


    public RPGame (GameEngine arg0) {
        super(arg0);
    }


    public void initResources () {}


    public void render (Graphics2D g) {}


    public void update (long elapsed) {}


    public Player getPlayer () {
        return player;
    }

}
