package collisions;

import enemy.Enemy;
import java.util.ArrayList;
import java.util.List;

import player.Player;
import app.RPGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class EnemyCollision extends BasicCollisionGroup {

    private RPGame game;
    private Enemy enemy;
    private Player player;

    public EnemyCollision (RPGame game, Player player, Enemy enemy, String enemyname) {
        this.game = game;
        this.player = player;
        this.enemy = enemy;
    }

    public void collided (Sprite character, Sprite enemy) {
        overlap(character, enemy);
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
