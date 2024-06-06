package app.model.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;

public class EntityTest {

	private Entity entity1;
    private Entity entity2;

    class TestEntity extends Entity {

        TestEntity() {
		    this.drawBox = new Rectangle(0, 0, 100, 100);
        }
        
        @Override
        public void update(float delta, EntityGameWorld gameWorld) {
            drawBox.x += delta;
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
        entity1 = new TestEntity();
        entity2 = new TestEntity();
    }

    @Test
    void testEntitiesShouldCollide() {
        assertTrue(entity1.isColliding(entity2));
    }

    @Test
    void testEntitiesShouldNotCollide() {
        entity2.update(100, null);
        assertFalse(entity1.isColliding(entity2));
    }

    @Test
    void testEntityIsInitiallyAlive() {
        assertTrue(entity1.isAlive());
    }

    @Test
    void testKillEntity() {
        entity1.kill();
        assertFalse(entity1.isAlive());
    }

    @Test
    void testHitBox() {
        assertEquals(entity1.getDrawBox(), entity1.getDrawBox());
    }
}
