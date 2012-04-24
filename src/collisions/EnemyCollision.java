package collisions;

import java.util.ArrayList;
import java.util.List;

import player.Player;
import app.RPGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import gameCharacter.Enemy;


public class EnemyCollision extends BasicCollisionGroup {

    private RPGame game;
    private Enemy enemy;
    private Player player;

    public EnemyCollision (RPGame game, Player player, String enemyname) {
        this.game = game;
        this.player = player;
        this.enemy = game.getLevel().getEnemy(enemyname);
    }


    public void collided (Sprite character, Sprite scenery) {
        overlap(character, scenery);
    
        
        if (player.getActions().isAttacking()) {
            enemy.addToHealth(-1);
            if (enemy.getHealth() < 1) {
                enemy.die();
                game.getField().removeCollisionGroup(this);
            }
            jump(character, scenery);
        }
        else {
            jump(character, scenery);
            enemy.onCollision();
        }
    }

    protected void jump (Sprite character, Sprite scenery) {
        scenery.setX(character.getX() - 100);
        scenery.setY(character.getY() - 100);
    }

    protected void overlap (Sprite character, Sprite scenery) {
        double maxsep =
            scenery.getImage().getHeight() - character.getImage().getHeight() /
                    6 * 5;
        double separation = character.getY() - scenery.getY();

        if (separation <= maxsep) {
            character.setX(character.getOldX());
            character.setY(character.getOldY());
        }
    }

}
