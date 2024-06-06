package app.model.entities.enemies;

import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;


public class ObstacleTest {

	private Obstacle entity;

	@BeforeEach
	void setUp() {
		entity = new Obstacle();
	}

	@Test
	void testPositionInitialization() {
		assertEquals(Constants.OBSTACLE_SIZE, entity.getDrawBox().width, 0.1);
		assertEquals(Constants.OBSTACLE_SIZE, entity.getDrawBox().height, 0.1);
		assertEquals(Constants.BOARD_WIDTH + Constants.OBSTACLE_SIZE, entity.getDrawBox().x);
	}

	@Test
	void testPositionBounds() {
		int heightLimit = Constants.BOARD_HEIGHT - Constants.OBSTACLE_SIZE;
		for (int i = 0; i < 500; i++) {
			entity = new Obstacle();
			assertTrue(heightLimit > entity.getDrawBox().y);
			assertTrue(0 <= entity.getDrawBox().y);
		}
	}

	@Test
	void testUpdate() {
		float delta = .5f;
		float expectedDiff = Constants.OBSTACLE_SPEED * delta;
		float prevX = entity.getDrawBox().x;
		entity.update(delta, null);
		float actualDiff = prevX - entity.getDrawBox().x;
		assertEquals(expectedDiff, actualDiff, 0.1); 
	}

	@Test
	void testGetHitBox() {
		Rectangle expectedHitBox = entity.getDrawBox();
		expectedHitBox.x = expectedHitBox.x + expectedHitBox.width / 5;
        expectedHitBox.y = expectedHitBox.y + expectedHitBox.height / 5;
        expectedHitBox.width = expectedHitBox.width / 2;
        expectedHitBox.height = expectedHitBox.height / 1.75f;
		assertEquals(expectedHitBox, entity.getHitBox());
	}

	@Test
	void testGetKey() {
		assertEquals(Constants.OBSTACLE_KEY, entity.getKey());
	}
}
