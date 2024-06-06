package app.model.entities.enemies.laser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Rectangle;
import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

public class LaserWarningTest {

    @Test
    void testLaserWarningInitialization() {
        int slot = 2;
        LaserWarning laserWarning = new LaserWarning(slot);
        float expectedSize = Constants.BOARD_HEIGHT / Constants.NUMBER_OF_LASER_SLOTS;
        float expectedX = Constants.BOARD_WIDTH - expectedSize;
        float expectedY = expectedSize * slot;
        Rectangle expectedDrawBox = new Rectangle(expectedX, expectedY, expectedSize, expectedSize);
        assertEquals(expectedDrawBox, laserWarning.getDrawBox(), "Draw box should be initialized correctly");
        assertEquals(Constants.LASER_WARNING_KEY, laserWarning.getKey(), "Key character should be set correctly");
    }

    @Test
    void testLaserWarningUpdateSpawnsLaserBeamWhenTimerRunsOut() {
        EntityGameWorld mockGameWorld = mock(EntityGameWorld.class);
        LaserWarning laserWarning = new LaserWarning(0);
        float duration = Constants.LASER_WARNING_DURATION;
        
        laserWarning.update(.1f, mockGameWorld);
        assertTrue(laserWarning.isAlive());
        laserWarning.update(duration, mockGameWorld);
        assertFalse(laserWarning.isAlive());
        verify(mockGameWorld, times(1)).spawnEntity(any(LaserBeam.class));
    }
}
