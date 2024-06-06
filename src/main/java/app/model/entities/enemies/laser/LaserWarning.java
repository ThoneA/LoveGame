package app.model.entities.enemies.laser;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * Stays as a passive warning until the timer runs out and then creates a laser
 * beam.
 */
public class LaserWarning extends TimedFoe {

    private int slot;

    /**
     * Creates a new laser warning a the given slot
     * 
     * @param slot
     */
    public LaserWarning(int slot) {
        this.slot = slot;
        duration = Constants.LASER_WARNING_DURATION;
        initializeDrawBox();
    }

    private void initializeDrawBox() {
        float size = Constants.BOARD_HEIGHT / Constants.NUMBER_OF_LASER_SLOTS;
        float x = Constants.BOARD_WIDTH - size;
        float y = size * slot;
        drawBox = new Rectangle(x, y, size, size);
    }

    @Override
    public char getKey() {
        return Constants.LASER_WARNING_KEY;
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        super.update(delta, gameWorld);
        if (!alive) {
            gameWorld.spawnEntity(new LaserBeam(slot));
        }
    }
}
