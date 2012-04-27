package enemy;

import state.AttackingState;
import state.DiscoveryState;
import state.MovingState;
import ai.BoolDistanceFromTargetHeuristic;
import ai.GreedyPathFindingAI;
import ai.TestDecisionTableAI;
import app.RPGame;

import com.google.gson.JsonObject;

import gameCharacter.GameCharacter;

public class ArcherEnemy extends AbstractEnemy{

	private static final int DEFAULT_MONEY_VALUE = 10;
	private static final double DIST_THRESHOLD = 130;
	private static final double DISCOVERY_THRESHOLD = 200;

	public ArcherEnemy(RPGame game, GameCharacter character, JsonObject jEnemy) {
		super(game, character, "ArcherEnemy", jEnemy);
		initResources();
		moneyValue = DEFAULT_MONEY_VALUE;
	}
	
	@Override
	public JsonObject getJsonAttributes() {
		JsonObject attrib = new JsonObject();
		/*
		 * Add any subclass-specific variables here
		 */
		return attrib;
	}
	
	public void initAI(String json) {
		addState(new DiscoveryState(new BoolDistanceFromTargetHeuristic(game,character,DISCOVERY_THRESHOLD,true)));
		addState(new MovingState(new GreedyPathFindingAI(game, character), 
				new BoolDistanceFromTargetHeuristic(game,character,DIST_THRESHOLD,false)));
		addState(new AttackingState(new TestDecisionTableAI(game, this),
				new BoolDistanceFromTargetHeuristic(game,character,DIST_THRESHOLD,true)));
	}

	public static class ArcherEnemyFactory extends EnemyFactory{
	
		@Override
		public boolean isThisType(String enemyName) {
			return "ArcherEnemy".equals(enemyName);
		}
	
		@Override
		public AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar, JsonObject jEnemy) {
			return new ArcherEnemy(game, gameChar, jEnemy);
		}
		
	}

}
