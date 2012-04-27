package enemy;



import gameCharacter.Attackable;
import gameCharacter.GameCharacter;

import java.util.HashMap;

import state.MovingAttackingState;
import state.State;

import com.golden.gamedev.Game;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import ai.GreedyPathFindingAI;
import ai.SimpleAttackAI;

import app.RPGame;
import attacks.AbstractAttack;
import attacks.ShootingAttack;

/**
 * GameCharacter decorated with attacks and actions.
 * Can use pluggable AI algorithms to behave automatically.
 * @author jameshong
 *
 */
public class TestEnemy extends AbstractEnemy{

	private static final int DEFAULT_MONEY_VALUE = 10;

	public TestEnemy(RPGame game, GameCharacter character, JsonObject jEnemy) {
		super(character, "TestEnemy", jEnemy);
		this.game = game;
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
		setCurrentState(new MovingAttackingState(new GreedyPathFindingAI(game, this.getCharacter()), new SimpleAttackAI(game,this)));
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
