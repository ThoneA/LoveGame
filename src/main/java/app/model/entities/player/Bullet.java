package app.model.entities.player;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * Entity meant for shooting foes, moves from left to right.
 */
public class Bullet extends Entity {

    /**
     * Creates a new bullet at the right of the player and centered to the height of
     * the player.
     */
    public Bullet(EntityGameWorld gameWorld) {
        Rectangle playerHitBox = gameWorld.getPlayerHitBox();
        int size = Constants.BULLET_SIZE;
        float x = playerHitBox.x + playerHitBox.width;
        float y = playerHitBox.y + (playerHitBox.height / 2);
        this.drawBox = new Rectangle(x, y, size, size);
    }

    @Override
    public char getKey() {
        return Constants.BULLET_KEY;
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        drawBox.x += Constants.BULLET_SPEED * delta;
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER_WEAPON;
    }

}
