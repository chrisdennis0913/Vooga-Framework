package collisions;

import npc.NPC;
import player.Player;
import app.RPGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import enemy.Enemy;


public class ProjectileCollision extends BasicCollisionGroup {
    private RPGame game;
    private Enemy enemy;
    private Player player;
    private NPC npc;


    public ProjectileCollision (RPGame game, Player player, Enemy enemy, String enemyname) {
        this.game = game;
        this.player = game.getPlayer();
        this.enemy = enemy;
    }

    @Override
    public void collided (Sprite playerProjectile, Sprite badGuy) {
        if (enemy != null) {
            enemy.addToHealth(-1);
        }
        else npc.die();
        playerProjectile.setActive(false);
    }
}
