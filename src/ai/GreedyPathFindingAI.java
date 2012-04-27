package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.golden.gamedev.object.Timer;

import level.Level;
import player.Player;
import utils.Location;
import gameCharacter.GameCharacter;
import app.RPGame;

/**
 * Path finder that chooses and executes the action
 * that brings the enemy closer to the player.
 * @author jameshong
 *
 */
public class GreedyPathFindingAI extends AbstractMovementAI{

	public static final int TILE_WIDTH = 10;
	public static final int TILE_HEIGHT = 10;
	private static final int CALCULATION_INTERVAL = 100;
	private static final double MINIMUM_DISTANCE = 20;
	
	Player player;
	Timer calcInterval;
	
	public GreedyPathFindingAI(RPGame game, GameCharacter character) {
		super(game, character);
		this.player = game.getPlayer();
		calcInterval = new Timer(CALCULATION_INTERVAL);
	}
	
	public int nextAction(){
		Location currTile = character.getLocation();
		Location playerTile = player.getCharacter().getLocation();
		List<ActionTransition> adjActions = getAdjacentActions(currTile,playerTile);
				
		if(adjActions.get(0) != null && adjActions.get(0).distToGoal() > MINIMUM_DISTANCE){
			return adjActions.get(0).direction;
		}
		else return -1;
	}
	
	public List<ActionTransition> getAdjacentActions(Location currTile,Location goalTile){
		
		ArrayList<ActionTransition> adjActions = new ArrayList<ActionTransition>();
		for(int i = 0; i <= 3; i++){
			ActionTransition nextAction = 
					new ActionTransition(currTile,goalTile,i);
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
		if(!calcInterval.action(elapsedTime))
			return;
		int nextDirection = nextAction();

		if ((!character.isActive() || nextDirection != character.getCurrentDirection())
				&& nextDirection != -1){
			setActive(true);
			character.setActiveDirection(nextDirection);
			character.setVelocity(0.05);
		} else if (nextDirection == -1){
			setActive(false);
			character.stop();
		}
	}
	
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
