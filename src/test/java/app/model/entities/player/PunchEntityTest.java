package app.model.entities.player;

import app.model.entities.entityhandling.EntityGameWorld;
import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;

public class PunchEntityTest {
        
    private Entity entity;
    private MockEntityGameWorld gameWorld;

    class MockEntityGameWorld implements EntityGameWorld {

        private float y;

        public void update(float delta) {
            y += delta;
        }

        @Override
        public Rectangle getPlayerHitBox() {
            return new Rectangle(0, y, 100, 100);
        }

        @Override
        public void spawnEntity(Entity entity) {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'spawnEntity'");
        }

        @Override
        public void oneUp() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'oneUp'");
        }
    }

    @BeforeEach
    void setUp() {
        gameWorld = new MockEntityGameWorld();
        entity = new PunchEntity(gameWorld);
    }

    @Test
    void testInitialPosition() {
        entity = new PunchEntity(gameWorld);
        Rectangle expectedDrawBox = new Rectangle();
        expectedDrawBox.width = Constants.PLAYER_WIDTH;
        expectedDrawBox.height = Constants.PLAYER_HEIGHT;
        expectedDrawBox.x = Constants.PLAYER_STARTING_POSITION.x;
        expectedDrawBox.y = gameWorld.getPlayerHitBox().y;
        assertEquals(expectedDrawBox, entity.getDrawBox());
    }

    @Test
    void testFollowingPlayer() {
        float delta = .025f;
        float time = 0;
        while (time < 2) {
            time += delta;
            gameWorld.update(delta);
            entity.update(delta, gameWorld);
            assertEquals(gameWorld.getPlayerHitBox().y, entity.getDrawBox().y); 
        }
    }

    @Test
    void testGetType() {
        assertEquals(EntityType.PLAYER_WEAPON, entity.getType());
    }

    @Test
	void testGetKey() {
		assertEquals(Constants.PUNCH_ENTITY_KEY, entity.getKey());
	}
}
