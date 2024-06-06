package app.model.entities.enemies;

import app.model.entities.Entity;
import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BouncingEnemyTest {

    private Entity entity;

    @BeforeEach
    void setUpBeforeEach() {
        entity = new BouncingEnemy();
    }

    @Test
    void testPositionInitialization() {
        assertEquals(Constants.OBSTACLE_SIZE, entity.getDrawBox().width, 0.1);
        assertEquals(Constants.OBSTACLE_SIZE, entity.getDrawBox().height, 0.1);
        assertEquals(Constants.BOARD_WIDTH + Constants.OBSTACLE_SIZE, entity.getDrawBox().x);
    }

    @Test
    void testPositionBounds() {
        int minHeight = Constants.PLAYER_HEIGHT;
        int heightLimit = Constants.BOARD_HEIGHT - Constants.OBSTACLE_SIZE;
        for (int i = 0; i < 500; i++) {
            entity = new BouncingEnemy();
            assertTrue(heightLimit > entity.getDrawBox().y);
            assertTrue(minHeight <= entity.getDrawBox().y);
        }
    }

    @Test
    void testUpdateDeltaX() {
        float delta = .5f;
        float expectedDiff = Constants.OBSTACLE_SPEED * delta;
        float prevX = entity.getDrawBox().x;
        entity.update(delta, null);
        float actualDiff = prevX - entity.getDrawBox().x;
        assertEquals(expectedDiff, actualDiff, 0.1);
    }

    @Test
    void testBouncing() {
        float delta = .02f;
        float time = 0;
        float topHeight = entity.getDrawBox().y;
        boolean hasHitTheGround = false;
        boolean hasReachedTopHeight = false;
        while (time < 10) {
            time += delta;
            entity.update(delta, null);
            float y = entity.getDrawBox().y;
            assertTrue(y >= 0);
            assertTrue(y <= topHeight);
            if (y == 0) {
                hasHitTheGround = true;
            } else if (Math.abs(y - topHeight) < 1) {
                hasReachedTopHeight = true;
            }
        }
        assertTrue(hasHitTheGround);
        assertTrue(hasReachedTopHeight);
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.BOUNCING_ENEMY_KEY, entity.getKey());
    }
}
