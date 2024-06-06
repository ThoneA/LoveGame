package app.model.entities.player;


import app.utils.Constants;
import app.model.entities.EntityType;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.model.entities.Entity;

public class BulletTest {
    
    private Entity bullet;
    private EntityGameWorld gameWorld;

    class MockEntityGameWorld implements EntityGameWorld {

        @Override
        public Rectangle getPlayerHitBox() {
            return new Rectangle(0, 0, 100, 100);
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
        bullet = new Bullet(gameWorld);
    }

    @Test
    void testBulletPositionInitialization() {
        Rectangle bulletDrawBox = bullet.getDrawBox();
        Rectangle playerHitBox = gameWorld.getPlayerHitBox();
        int size = Constants.BULLET_SIZE;
        float x = playerHitBox.x + playerHitBox.width;
        float y = playerHitBox.y + (playerHitBox.height / 2);
        Rectangle expectedDrawBox = new Rectangle(x, y, size, size);
        assertEquals(expectedDrawBox, bulletDrawBox);
    }

    @Test
    void testUpdate() {
        float delta = .5f;
		float expectedDiff = Constants.BULLET_SPEED * delta;
		float prevX = bullet.getDrawBox().x;
		bullet.update(delta, null);
		float actualDiff = bullet.getDrawBox().x - prevX;
		assertEquals(expectedDiff, actualDiff, 0.1); 
    }
    
    @Test
    void testGetType() {
        assertEquals(EntityType.PLAYER_WEAPON, bullet.getType());
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.BULLET_KEY, bullet.getKey());
    }
}
