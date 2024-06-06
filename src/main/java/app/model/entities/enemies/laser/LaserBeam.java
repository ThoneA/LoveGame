package app.model.entities.enemies.laser;

import com.badlogic.gdx.math.Rectangle;

import app.utils.Constants;

/**
 * A beam that goes across the screen.
 */
public class LaserBeam extends TimedFoe {

    /**
     * Creates a new laser beam at the given slot.
     * 
     * @param slot
     */
    public LaserBeam(int slot) {
        duration = Constants.LASER_BEAM_DURATION;
        initializeDrawBox(slot);
    }

    private void initializeDrawBox(int slot) {
        float x = 0;
        float width = Constants.BOARD_WIDTH;
        float height = Constants.LASER_BEAM_HEIGHT;
        float slotSize = Constants.BOARD_HEIGHT / Constants.NUMBER_OF_LASER_SLOTS;
        float y = slotSize * slot + slotSize / 2 - height;
        drawBox = new Rectangle(x, y, width, height);
    }

    @Override
    public char getKey() {
        return Constants.LASER_BEAM_KEY;
    }

}
