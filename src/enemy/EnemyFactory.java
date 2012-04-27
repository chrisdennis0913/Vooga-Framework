package enemy;

import gameCharacter.GameCharacter;
import app.RPGame;

import com.google.gson.JsonObject;

public abstract class EnemyFactory {
	
	public abstract boolean isThisType(String enemyName);
	public abstract AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar,
			JsonObject jEnemy);

}
