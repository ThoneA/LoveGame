package app.model.entities.enemies;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * This entity is equivalent in size and spawning place to the obstacle entity,
 * but follows the player instead of just going straight forward.
 */
public class FollowingEnemy extends Obstacle {

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        Rectangle playerHitBox = gameWorld.getPlayerHitBox();

        float playerMiddleY = playerHitBox.y + playerHitBox.height / 2;
        float middle = drawBox.y + drawBox.height / 2;

        float diffX = drawBox.x - playerHitBox.x;
        float diffX2 = diffX * diffX;
        float diffY = playerMiddleY - middle;
        float diffY2 = diffY * diffY;

        if (diffX < Constants.ENEMY_FOLLOW_STOP) {
            drawBox.x -= Constants.ENEMY_SPEED * delta;
            return;
        }

        float hypotenuse = diffX2 + diffY2;
        float ratio = Constants.ENEMY_SPEED / hypotenuse;

        drawBox.x -= ratio * diffX2 * delta;

        int dir = diffY > 0 ? 1 : -1;
        drawBox.y += ratio * diffY2 * dir * delta;
    }

    @Override
    public char getKey() {
        return Constants.FOLLOWING_ENEMY_KEY;
    }

}
