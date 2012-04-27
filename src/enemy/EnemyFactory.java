package enemy;

import com.google.gson.JsonObject;

import app.RPGame;
import npc.NPC;
import gameCharacter.GameCharacter;

public abstract class EnemyFactory {
	
	public abstract boolean isThisType(String enemyName);
	public abstract AbstractEnemy constructEnemy(RPGame game, GameCharacter gameChar,
			JsonObject jEnemy);

}
