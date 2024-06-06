package app.model.entities.enemies;

import app.model.entities.entityhandling.EntityGameWorld;
import app.model.entities.Entity;
import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;

public class FollowingEnemyTest {
    
    private Entity entity;
    
    class MockEntityGameWorld implements EntityGameWorld {

        private float y;

        public void switchHeight() {
            y = Constants.BOARD_HEIGHT;
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
        entity = new FollowingEnemy();
    }

    @Test
    void testFollowingPlayerOnGround() {
        MockEntityGameWorld gameWorld = new MockEntityGameWorld();
        float delta = .025f;
        float time = 0;
        while (time < 10) {
            time += delta;
            float playerCenter = getCenterY(gameWorld.getPlayerHitBox());
            float enemyCenter = getCenterY(entity.getDrawBox());
            float prevDiff = Math.abs(playerCenter - enemyCenter);
            entity.update(delta, gameWorld);
            enemyCenter =  getCenterY(entity.getDrawBox());
            float diff = Math.abs(playerCenter - enemyCenter);
            assertTrue(diff <= prevDiff);
        }
    }

    @Test
    void testFollowingPlayerOnCeiling() {
        MockEntityGameWorld entityHandler = new MockEntityGameWorld();
        entityHandler.switchHeight();
        float delta = .025f;
        float time = 0;
        while (time < 10) {
            time += delta;
            float playerCenter = getCenterY(entityHandler.getPlayerHitBox());
            float enemyCenter = getCenterY(entity.getDrawBox());
            float prevDiff = Math.abs(playerCenter - enemyCenter);
            entity.update(delta, entityHandler);
            enemyCenter =  getCenterY(entity.getDrawBox());
            float diff = Math.abs(playerCenter - enemyCenter);
            assertTrue(diff <= prevDiff);
        }
    }

    private float getCenterY(Rectangle box) {
        float center = box.y + box.height / 2;
        return center;
    }

    @Test
	void testGetKey() {
		assertEquals(Constants.FOLLOWING_ENEMY_KEY, entity.getKey());
	}
}
