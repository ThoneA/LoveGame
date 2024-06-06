package app.model.entities.enemies;

import java.util.Random;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * This entity is equivalent in size and spawning place to the obstacle entity,
 * but bounces on the ground instead of just going straight forward.
 */
public class BouncingEnemy extends Obstacle {

    private float topHeight;
    private float verticalVelocity;

    /**
     * Creates a new bouncing enemy to the right of the board at a random position
     * between the player's height and the top of the board.
     */
    public BouncingEnemy() {
        super();
        initializeHeight();
    }

    private void initializeHeight() {
        Random rnd = new Random();
        int minHeight = Constants.PLAYER_HEIGHT;
        int upperBound = Constants.BOARD_HEIGHT - (int) drawBox.height;
        drawBox.y = rnd.nextInt(minHeight, upperBound);
        topHeight = drawBox.y;
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        super.update(delta, gameWorld);
        verticalVelocity -= Constants.GRAVITY * delta;
        drawBox.y += verticalVelocity * delta;
        if (drawBox.y <= 0) {
            drawBox.y = 0;
            verticalVelocity = (float) Math.sqrt(2 * Constants.GRAVITY * topHeight);
        }
    }

    @Override
    public char getKey() {
        return Constants.BOUNCING_ENEMY_KEY;
    }
}
