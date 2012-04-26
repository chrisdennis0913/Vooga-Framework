package ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
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
		System.err.println(level);
		this.player = game.getPlayer();
	}
	
	public int nextAction(){
		Point currTile = level.getTileAt(character.getX(), character.getY());
		Point playerTile = level.getTileAt(player.getCharacter().getX(), player.getCharacter().getY());
		List<ActionTransition> adjActions = getAdjacentActions(currTile,playerTile);
				
		if(adjActions.get(0) != null){
			return adjActions.get(0).direction;
		}
		else return -1;
	}
	
	public List<ActionTransition> getAdjacentActions(Point currTile,Point goalTile){
		
		ArrayList<ActionTransition> adjActions = new ArrayList<ActionTransition>();
		for(int i = 0; i < 3; i++){
			ActionTransition nextAction = 
					new ActionTransition(currTile,goalTile,i,level);
			if(!adjActions.contains(nextAction) && nextAction.intermTile != null)
				adjActions.add(nextAction);
		}
		Collections.sort(adjActions);
		return adjActions;
	}
	
	@Override
	public void initResources() {
	}

	@Override
	public void update(long elapsedTime) {
		int nextDirection = nextAction();
		if(nextDirection >= 0 && nextDirection <= 3)
			character.setCurrentDirection(nextDirection);			
	}
	
	public static class ActionTransition implements Comparable<ActionTransition>{
		Point startTile;
		Point goalTile;
		Point intermTile;
		int direction;
		Level level;
		
		//deltas for DIR_DOWN, DIR_UP, DIR_LEFT, DIR_RIGHT
		static int[] xdelta = new int[] {0,0,-1,1};
		static int[] ydelta = new int[] {1,-1,0,0};
		
		public ActionTransition(Point start, Point goal, int direction, Level level){
			this.startTile = start;
			this.goalTile = goal;
			this.direction = direction;
			this.level = level;
			calculateIntermTile();
		}
		
		public boolean equals(ActionTransition at){
			return startTile.equals(at.startTile) &&
					goalTile.equals(at.goalTile) &&
					intermTile.equals(at.intermTile);
		}
		
		private void calculateIntermTile(){
			int tileHeight = level.getTileHeight();
			int tileWidth = level.getTileWidth();
			intermTile = level.getTileAt(startTile.x+xdelta[direction]*tileWidth, 
					startTile.y+ydelta[direction]*tileHeight);
		}

		public double distToGoal(){
			if(intermTile == null)
				return Double.MAX_VALUE;
			return intermTile.distance(goalTile);
		}
		
		@Override
		public int compareTo(ActionTransition at) {
			if(this.distToGoal() > at.distToGoal())
				return 1;
			else if(this.distToGoal() < at.distToGoal())
				return -1;
			else
				return 0;
		}
	}

}
