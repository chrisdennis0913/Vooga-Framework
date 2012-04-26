package ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import level.Level;
import player.Player;
import gameCharacter.GameCharacter;
import app.RPGame;

public class AbstractPathFindingAI extends AbstractMovementAI{

	Level level;
	Player player;
	
	public AbstractPathFindingAI(RPGame game, GameCharacter character) {
		super(game, character);
		this.level = game.getLevel();
		this.player = game.getPlayer();
	}
	
	public int nextAction(){
		double distToPlayer = player.getDistance(character);
		Point currTile = level.getTileAt(character.getX(), character.getY());
		List<Point> adjTiles = getAdjacentTiles(currTile);
		for(Point p:adjTiles){
			
		}
	}
	
	public List<Point> getAdjacentTiles(Point tile){
		int tileHeight = level.getTileHeight();
		int tileWidth = level.getTileWidth();
		double charX = character.getX();
		double charY = character.getY();
		
		int[] rdelta = new int[] {0,0,1,-1};
		int[] cdelta = new int[] {1,-1,0,0};
		
		ArrayList<Point> adjTiles = new ArrayList<Point>();
		for(int i = 0; i < rdelta.length; i++){
			Point nextTile = level.getTileAt(charX+cdelta[i]*tileWidth, charY+rdelta[i]*tileHeight);
			if(!adjTiles.contains(nextTile) && level.isOccupied(nextTile.x, nextTile.y))
				adjTiles.add(nextTile);
		}
		return adjTiles;
	}
	
	@Override
	public void initResources() {
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	
}
