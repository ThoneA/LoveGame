package app.model.entities.enemies.laser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

public class LaserBeamTest {

    @Test
    void testLaserBeamInitialization() {
        int slot = 2; 
        LaserBeam laserBeam = new LaserBeam(slot);

        float expectedHeight = Constants.LASER_BEAM_HEIGHT;
        float expectedSlotSize = Constants.BOARD_HEIGHT / Constants.NUMBER_OF_LASER_SLOTS;
        float expectedY = expectedSlotSize * slot + expectedSlotSize / 2 - expectedHeight;
        Rectangle expectedDrawBox = new Rectangle(0, expectedY, Constants.BOARD_WIDTH, expectedHeight);
        assertEquals(expectedDrawBox, laserBeam.getDrawBox(), "Draw box should be initialized correctly");
        assertEquals(Constants.LASER_BEAM_KEY, laserBeam.getKey(), "Key character should be set correctly");
    }

    @Test
    void testLaserBeamUpdate() {
        LaserBeam laserBeam = new LaserBeam(0);
        EntityGameWorld mockGameWorld = null; 
        assertDoesNotThrow(() -> laserBeam.update(0, mockGameWorld), "Update method should not throw an exception");
    }

    @Test
    void testDuration() {
        LaserBeam laserBeam = new LaserBeam(0);
        float duration = Constants.LASER_BEAM_DURATION;
        
        laserBeam.update(.1f, null);
        assertTrue(laserBeam.isAlive());
        laserBeam.update(duration, null);
        assertFalse(laserBeam.isAlive());
    }
}