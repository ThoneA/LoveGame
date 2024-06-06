package app.model.entities.enemies.laser;

import app.utils.Constants;
import app.model.entities.entityhandling.EntityGameWorld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaserSpawnerTest {

    private LaserSpawner spawner;
    private EntityGameWorld mockGameWorld;

    @BeforeEach
    void setUp() {
        spawner = new LaserSpawner();
        mockGameWorld = mock(EntityGameWorld.class);
    }

    @Test
    void testUpdateSpawnsCorrectNumberOfLasers() {
        spawner.update(0, mockGameWorld);
        assertFalse(spawner.isAlive());
        int numberOfSlots = Constants.NUMBER_OF_LASER_SLOTS;
        int expectedNumberOfLasers = (int) Math.ceil(numberOfSlots / 2.0);

        verify(mockGameWorld, times(expectedNumberOfLasers)).spawnEntity(any(LaserWarning.class));
    }

}
