package app.model.entities.enemies;

import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.model.entities.entityhandling.EntityGameWorld;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class FoeTest {

    private Entity entity;

    class TestFoe extends Foe {

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public void update(float delta, EntityGameWorld gameWorld) {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

    }

    @Test
    void testGetType() {
        entity = new TestFoe();
        assertEquals(EntityType.FOE, entity.getType());
    }
}
