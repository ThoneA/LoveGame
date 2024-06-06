package app.model.entities.enemies;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * A taller and slower variant of the obstacle class.
 */
public class Wall extends Obstacle {

    /**
     * Creates a new wall to the right of the board at a random height between 0 and
     * the board height.
     */
    public Wall() {
        Random rnd = new Random();
        int width = Constants.WALL_WIDTH;
        int height = Constants.WALL_HEIGHT;
        int x = Constants.BOARD_WIDTH + width;
        int heightLimit = Constants.BOARD_HEIGHT - height;
        int y = rnd.nextInt(heightLimit);
        this.drawBox = new Rectangle(x, y, width, height);
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        drawBox.x -= Constants.WALL_SPEED * delta;
    }

    @Override
    public char getKey() {
        return Constants.WALL_KEY;
    }
}
