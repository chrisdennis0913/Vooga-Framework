package attacks;

import java.awt.image.BufferedImage;
import app.RPGame;
import attacks.AbstractAttackVector;
import attacks.AbstractVectorAttack;

import com.golden.gamedev.object.AnimatedSprite;

public class ShotVector extends AbstractAttackVector {

	private static final String imagePath = "rsc/items/sword.gif";
	
	public ShotVector(RPGame game, AbstractVectorAttack owner, double x, double y, double speedX, double speedY) {
		super(game, owner,x,y, speedX, speedY);
	}

	@Override
	public AnimatedSprite loadVectorSprite() {
		BufferedImage[] image = new BufferedImage[] { game
				.getImage(imagePath) };
		return new AnimatedSprite(image);
	}

}
