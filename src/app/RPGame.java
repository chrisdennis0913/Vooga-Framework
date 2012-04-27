package app;

import inventory.Item;

import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import level.Level;
import player.Player;
import quest.Quest;
import quest.QuestObserver;
import utils.JsonUtil;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.google.gson.JsonObject;


public class RPGame extends GameObject {

	protected static String gameURL;

	public PlayField field = new PlayField();

	private Set<Pausable> paused;
	private Player player;
	protected Level level;

	String lower, upper;

	public RPGame(GameEngine parent, String configURL) {
		super(parent);
		gameURL = configURL;
	}

	public void initResources() {
		JsonObject gameJson = JsonUtil.getJSON(gameURL);
		
		level = new Level(bsLoader, bsIO, this, gameJson.get("level").getAsString());

		field.setComparator(new Comparator<Sprite>() {
			public int compare(Sprite o1, Sprite o2) {
				if (o1 instanceof Item)
					return -1;
				else if (o2 instanceof Item)
					return 1;
				return (int) (o1.getY() - o2.getY());
			}
		});
		field.setBackground(level);
		paused = new HashSet<Pausable>();
	}

	public void render(Graphics2D g) {
		field.render(g);
	      if (isPausedFor(Pausable.INV))
	      {
	            player.getCharacter().getInventory().render(g);
	            return;
	       }
	      if (isPausedFor(Pausable.JOURNAL))
	      {
	    	  player.getCharacter().getJournal().render(g);
	    	  return;
	      }
	}

	public void update(long elapsed) 
	{
	    if (isPausedFor(Pausable.INV))
	    {
	        player.getCharacter().getInventory().update(elapsed);
	        return;
	    }
	    
	    player.getCharacter().getJournal().update(elapsed);
	    
		field.update(elapsed);
		player.update(elapsed);
	}
	
	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void addQuest(Quest qu, QuestObserver qg) 
	{
		player.getCharacter().getJournal().addQuest(qu, qg);
	}

	public PlayField getField() {
		return field;
	}
	
	public void pauseGameFor(Pausable pau)
	{
		paused.add(pau);
	}
	
	public void unPauseGameFor(Pausable pau)
	{
		paused.remove(pau);
	}
	
	public boolean isPausedFor(Pausable pau)
	{
		return paused.contains(pau);
	}
	
	public enum Pausable
	{
		INV, STORE, JOURNAL;
	}
}