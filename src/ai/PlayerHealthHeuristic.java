package ai;

import gameCharacter.GameCharacter;
import app.RPGame;

public class PlayerHealthHeuristic extends AbstractGameHeuristic{

	public PlayerHealthHeuristic(RPGame game, GameCharacter character,
			String conditionName) {
		super(game, character, conditionName);
	}

	@Override
	public double getHeuristic() {
		return game.getPlayer().getCharacter().getCounters().get("health").getCount();
	}

}
