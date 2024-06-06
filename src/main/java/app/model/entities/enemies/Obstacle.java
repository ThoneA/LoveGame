package app.model.entities.enemies;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

import java.util.Random;

/**
 * Obstacle is a foe-entity that moves from right to left.
 */
public class Obstacle extends Foe {

    /**
     * Creates an Obstacle outside the board on the right side with at a random
     * height within the range of the board.
     */
    public Obstacle() {
        int size = Constants.OBSTACLE_SIZE;
        int x = Constants.BOARD_WIDTH + size;
        int heightLimit = Constants.BOARD_HEIGHT - size;
        Random rnd = new Random();
        int y = rnd.nextInt(heightLimit);
        this.drawBox = new Rectangle(x, y, size, size);
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        drawBox.x -= Constants.OBSTACLE_SPEED * delta;
    }

    @Override
    public char getKey() {
        return Constants.OBSTACLE_KEY;
    }

    @Override
    public Rectangle getHitBox() {
        float x = drawBox.x + drawBox.width / 5;
        float y = drawBox.y + drawBox.height / 5;
        float width = drawBox.width / 2;
        float height = drawBox.height / 1.75f;
        Rectangle hitBox = new Rectangle(x, y, width, height);
        return hitBox;
    }
}
