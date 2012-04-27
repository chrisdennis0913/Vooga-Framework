package enemy;

import gameCharacter.GameCharacter;
import state.MovingAttackingState;
import ai.GreedyPathFindingAI;
import ai.SimpleAttackAI;
import ai.TestDecisionTableAI;
import app.RPGame;

import com.google.gson.JsonObject;

/**
 * GameCharacter decorated with attacks and actions.
 * Can use pluggable AI algorithms to behave automatically.
 * @author jameshong
 *
 */
public class TestEnemy extends AbstractEnemy{

	private static final int DEFAULT_MONEY_VALUE = 10;

	public TestEnemy(RPGame game, GameCharacter character, JsonObject jEnemy) {
		super(game, character, "TestEnemy", jEnemy);
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
		setCurrentState(new MovingAttackingState(new GreedyPathFindingAI(game, this.getCharacter()), new TestDecisionTableAI(game,this)));
	}

	public static class TestEnemyFactory extends EnemyFactory{
	
		@Override
		public boolean isThisType(String enemyName) {
			return "TestEnemy".equals(enemyName);
		}
	
		@Override
		public AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar, JsonObject jEnemy) {
			return new TestEnemy(game, gameChar, jEnemy);
		}
		
	}

}
