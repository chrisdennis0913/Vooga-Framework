package enemy;

import gameCharacter.GameCharacter;
import state.GuardState;
import state.MovingState;
import ai.BoolDistanceFromTargetHeuristic;
import ai.GreedyPathFindingAI;
import ai.TestDecisionTableAI;
import app.RPGame;

import com.google.gson.JsonObject;


public class GuardEnemy extends AbstractEnemy{

	private static final int DEFAULT_MONEY_VALUE = 10;
	private static final double DIST_THRESHOLD = 200;

	public GuardEnemy(RPGame game, GameCharacter character, JsonObject jEnemy) {
		super(game, character, "GuardEnemy", jEnemy);
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
		addState(new MovingState(new GreedyPathFindingAI(game, character),
				new BoolDistanceFromTargetHeuristic(game,character,DIST_THRESHOLD,true)));
		addState(new GuardState(new BoolDistanceFromTargetHeuristic(game,character,DIST_THRESHOLD,false)));
	}

	public static class GuardEnemyFactory extends EnemyFactory{
	
		@Override
		public boolean isThisType(String enemyName) {
			return "GuardEnemy".equals(enemyName);
		}
	
		@Override
		public AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar, JsonObject jEnemy) {
			return new GuardEnemy(game, gameChar, jEnemy);
		}
		
	}

}
