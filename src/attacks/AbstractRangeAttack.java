package attacks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.golden.gamedev.object.Sprite;

import gameCharacter.GameCharacter;
import app.RPGame;

public abstract class AbstractRangeAttack extends AbstractAttack{
	
	private int minDistance;
	
	public AbstractRangeAttack(RPGame game, GameCharacter attacker, String name, int minDistance) {
		super(game, attacker, name);
		this.minDistance = minDistance;
	}

	public abstract void performAttackIndividual(long elapsedTime);

	@Override
	public final void performAttack(long elapsedTime) {
		for(GameCharacter gc: findTargets()){
			setTarget(gc);
			performAttackIndividual(elapsedTime);
		}
	}
	
	public boolean checkIfAttackable(GameCharacter gc){
		return attacker.getDistance(gc) < minDistance;
	}
	
	public List<GameCharacter> findTargets(String... attackableGroups){
		List<GameCharacter> targets = new ArrayList<GameCharacter>();
		for(String s : attackableGroups){
			for(Sprite sp: game.getField().getGroup(s).getSprites()){
				if(sp instanceof GameCharacter){
					GameCharacter gc = (GameCharacter) sp;
					if (checkIfAttackable(gc))
						targets.add(gc);
				}
			}
		}
		return targets;
	}
	

	
}
