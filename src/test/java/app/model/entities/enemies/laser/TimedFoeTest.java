package app.model.entities.enemies.laser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import app.model.entities.Entity;
import app.model.entities.EntityType;

public class TimedFoeTest {
    
    class TestTimedFoe extends TimedFoe {

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }
        
    }

    @Test
    void testGetType() {
        Entity entity = new TestTimedFoe();
        assertEquals(EntityType.TIMED_FOE, entity.getType());
    }
}
