package app.model.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TimedEntityTest {

    private TimedEntity entity;
    private float duration = 1;

    class TestTimedEntity extends TimedEntity {

        public TestTimedEntity(float duration) {
            this.duration = duration;
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public EntityType getType() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getType'");
        }

    }

    @BeforeEach
    void setUp() {
        entity = new TestTimedEntity(duration);
    }

    @Test
    void testUpdate() {
        entity.update(.1f, null);
        assertTrue(entity.isAlive());
        entity.update(duration, null);
        assertFalse(entity.isAlive());
    }
}
