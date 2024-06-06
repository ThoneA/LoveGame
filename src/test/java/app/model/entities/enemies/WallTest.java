package app.model.entities.enemies;

import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class WallTest {

	private Wall wall;

	@BeforeEach
	void setUp() {
		wall = new Wall();
	}

	@Test
	void testPositionInitialization() {
		assertEquals(Constants.WALL_WIDTH, wall.getDrawBox().width, 0.1);
		assertEquals(Constants.WALL_HEIGHT, wall.getDrawBox().height, 0.1);
		assertEquals(Constants.BOARD_WIDTH + Constants.WALL_WIDTH, wall.getDrawBox().x);
	}

	@Test
	void testPositionBounds() {
		int heightLimit = Constants.BOARD_HEIGHT - Constants.WALL_HEIGHT;
		for (int i = 0; i < 500; i++) {
			wall = new Wall();
			assertTrue(heightLimit > wall.getDrawBox().y);
			assertTrue(0 <= wall.getDrawBox().y);
		}
	}

	@Test
	void testUpdate() {
		float delta = .5f;
		float expectedDiff = Constants.WALL_SPEED * delta;
		float initialX = wall.getDrawBox().x;
		wall.update(delta, null);
		float actualDiff = initialX - wall.getDrawBox().x;
		assertEquals(expectedDiff, actualDiff, 0.1); 
	}

	@Test
	void testGetKey() {
		assertEquals(Constants.WALL_KEY, wall.getKey());
	}
}
