package player;

import actions.Attack;

public class BowAttack extends Attacking {

	private static final long serialVersionUID = 1L;

	public BowAttack(Attack attack) {
		super(attack);
	}

	public boolean isEnabled() {
		return getWrapper().getCharacter().getInventory().isEquipped("bow");
	}
	
}
