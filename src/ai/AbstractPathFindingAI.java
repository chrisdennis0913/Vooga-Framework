package ai;

import gameCharacter.GameCharacter;

import java.util.ArrayList;
import java.util.List;

import level.Level;
import player.Player;
import utils.Location;
import app.RPGame;

import com.golden.gamedev.object.Timer;

public abstract class AbstractPathFindingAI extends AbstractMovementAI {

	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	public static final int CALCULATION_INTERVAL = 50;

	protected Player player;
	protected Timer calcInterval;
	
	public AbstractPathFindingAI(RPGame game, GameCharacter character) {
		super(game, character);
	}
	
	@Override
	public void initResources() {
	}

	public abstract void update(long elapsedTime);

	public abstract int nextAction();
	
	public static class ActionTransition implements Comparable<ActionTransition>{
		Location startTile;
		Location goalTile;
		Location intermTile;
		int direction;
		Level level;
		
		//deltas for DIR_DOWN, DIR_UP, DIR_LEFT, DIR_RIGHT
		static int[] xdelta = new int[] {0,0,-1,1};
		static int[] ydelta = new int[] {1,-1,0,0};
		
		public ActionTransition(Location start, Location goal, int direction){
			this.startTile = start;
			this.goalTile = goal;
			this.direction = direction;
			calculateIntermTile();
		}
		
		public boolean equals(ActionTransition at){
			return startTile.equals(at.startTile) &&
					goalTile.equals(at.goalTile) &&
					intermTile.equals(at.intermTile);
		}
		
		private void calculateIntermTile(){
			intermTile = new Location(startTile.getX()+xdelta[direction]*TILE_WIDTH, 
					startTile.getY()+ydelta[direction]*TILE_HEIGHT);
		}
	
		public double distToGoal(){
			if(intermTile == null)
				return Double.MAX_VALUE;
			return intermTile.distance(goalTile);
		}
		
		public List<ActionTransition> getAllNextActions(){
			ArrayList<ActionTransition> next = new ArrayList<ActionTransition>();
			for(int i = 0; i <= 3; i++){
				next.add(new ActionTransition(intermTile,goalTile,i));
			}
			return next;
		}
		//TODO: check if actions are valid
		/*
		public List<ActionTransition> getValidNextActions(){
			for(ActionTransition at : getAllNextActions()){
				
			}
		}*/
		
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