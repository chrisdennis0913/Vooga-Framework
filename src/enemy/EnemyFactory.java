package enemy;

import com.google.gson.JsonObject;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class EnemyFactory {
	
	public abstract boolean isThisType(String enemyName);
	public abstract AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar,
			JsonObject jEnemy);

}
