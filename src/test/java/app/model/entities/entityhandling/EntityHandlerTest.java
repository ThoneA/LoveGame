
package app.model.entities.entityhandling;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import app.model.entities.Entity;
import app.model.entities.player.Player;
import app.utils.Constants;
import app.model.entities.EntityType;
import app.model.entities.enemies.Foe;
import app.model.entities.enemies.laser.TimedFoe;
import app.model.entities.items.Item;
import app.model.entities.items.ItemEffect;

import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Field;

import java.lang.reflect.Method;

public class EntityHandlerTest {

    private EntityHandler entityHandler;

    @Mock
    private EntityHandlerGameWorld mockGameWorld;
    @Mock
    private Player mockPlayer;
    @Mock
    private Entity mockEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockGameWorld.getLevel()).thenReturn(1);
        when(mockPlayer.isAlive()).thenReturn(true);
        when(mockPlayer.isStandingOnTheGround()).thenReturn(true);
        entityHandler = new EntityHandler(mockGameWorld);
        Rectangle hitBox = new Rectangle(0, 0, 100, 100);
        when(mockEntity.getHitBox()).thenReturn(hitBox);
    }

    @Test
    void testInitialization() {
        assertNotNull(entityHandler, "EntityHandler should be initialized with a non-null game world");
    }

    @Test
    void testUpdateWithEntityAddition() {
        EntityHandlerGameWorld mockGameWorld = mock(EntityHandlerGameWorld.class);
        when(mockGameWorld.getScore()).thenReturn(1); // Set a low or predictable score

        EntityHandler entityHandler = new EntityHandler(mockGameWorld);

        // Calculate delta to definitely trigger spawning
        float spawnFreq = Constants.SPAWNING_FREQUENCY_FOE;
        float spawnAcc = Constants.SPAWNING_ACCELERATION_FOE;
        float frequency = spawnFreq / (mockGameWorld.getScore() * spawnAcc + 1);
        float delta = frequency + 0.1f; // Ensure delta is larger than calculated frequency

        entityHandler.update(delta, true);

        assertFalse(entityHandler.getEntities().isEmpty(),
                "Entities should be added on update when shouldAddEntities is true and sufficient time has passed");
    }

    @Test
    void testPlayerStandingOnTheGround() {
        assertTrue(entityHandler.isPlayerStandingOnTheGround(),
                "Player should be reported as standing on the ground initially");
    }

    @Test
    void testIsEmptyWithNoEntities() {
        assertTrue(entityHandler.isEmpty(), "Should return true when no entities are present");
    }

    @Test
    void testPlayerCollisionWithFoeButPlayerIsInvincibleDoesNotDie()
            throws NoSuchFieldException, IllegalAccessException {
        EntityHandler entityHandler = new EntityHandler(mock(EntityHandlerGameWorld.class));
        Player mockPlayer = mock(Player.class);
        Entity mockFoe = mock(Entity.class);

        // Set up the mock player as invincible
        when(mockPlayer.isAlive()).thenReturn(true);
        when(mockPlayer.hasEffect(ItemEffect.INVINCIBLE)).thenReturn(true);
        when(mockFoe.getType()).thenReturn(EntityType.FOE);
        when(mockFoe.isColliding(mockPlayer)).thenReturn(true);
        when(mockFoe.isAlive()).thenReturn(true);
        when(mockFoe.getHitBox()).thenReturn(new Rectangle());
        entityHandler.spawnEntity(mockFoe);

        // Using reflection to set the player in EntityHandler
        Field playerField = EntityHandler.class.getDeclaredField("player");
        playerField.setAccessible(true);
        playerField.set(entityHandler, mockPlayer);
        // Execute the game update
        entityHandler.update(0.1f, false);
        // Verify that the player's kill method is not invoked
        verify(mockPlayer, never()).kill();
    }

    @Test
    void oneUpTest() {
        EntityHandler entityHandler = new EntityHandler(mock(EntityHandlerGameWorld.class));

        int initHealth = entityHandler.getPlayerHealth();
        assertEquals(initHealth, Constants.PLAYER_INIT_HEALTH,
                "Initial health should match the constant for player initial health");

        entityHandler.oneUp(); // This method should increment the player's health by 1

        assertEquals(initHealth + 1, entityHandler.getPlayerHealth(), "Player health should be incremented by 1");
    }

    @Test
    void testEntitySpawnFrequency() throws Exception {
        EntityHandler entityHandler = new EntityHandler(mock(EntityHandlerGameWorld.class));
        setPrivateField(entityHandler, "timeSinceFoe", 0f);
        setPrivateField(entityHandler, "timeSinceItem", 0f);

        // Update with small delta times to simulate rapid game updates
        float smallDeltaTime = 0.001f; // Smaller than any reasonable spawn frequency
        for (int i = 0; i < 1000; i++) {
            entityHandler.update(smallDeltaTime, true);
        }

        // Check that entities are not added more often than the spawn frequency allows
        assertTrue(entityHandler.getEntities().size() <= 1,
                "Entities should not spawn more frequently than configured");
    }

    // Helper method to set private fields via reflection
    private void setPrivateField(Object object, String fieldName, float value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.setFloat(object, value);
    }

    @Test
    void testPlayerHealthUnderflow() throws Exception {
        EntityHandler entityHandler = new EntityHandler(mock(EntityHandlerGameWorld.class), 1);

        // Reflection
        Method playerHitMethod = EntityHandler.class.getDeclaredMethod("playerHit");
        playerHitMethod.setAccessible(true);

        // Simulate multiple hits in a single update cycle
        playerHitMethod.invoke(entityHandler); // This should reduce health to 0
        playerHitMethod.invoke(entityHandler); // Additional hits should not reduce health below 0
        playerHitMethod.invoke(entityHandler); // Testing robustness with extra calls

        // Ensure player health doesn't underflow
        assertTrue(entityHandler.getPlayerHealth() >= 0, "Player health should not underflow below zero");
    }

    @Test
    void testPlayerRespawn() {
        // Simulate a player death with health equal to zero
        EntityHandler entityHandler = new EntityHandler(mock(EntityHandlerGameWorld.class), 0);
        entityHandler.reset(); // Reset to simulate respawn

        assertTrue(entityHandler.getPlayerHealth() == Constants.PLAYER_INIT_HEALTH,
                "Player should respawn with full health");
        assertTrue(entityHandler.isEmpty(), "All entities should be cleared on respawn");
    }

    @Test
    void testWeaponPlayerCollision() {
        float delta = .02f;

        Entity weapon = new MockWeapon(entityHandler.getPlayerHitBox());
        entityHandler.spawnEntity(weapon);
        entityHandler.update(delta, true);
        // Weapon not killed by collision with player
        assertEquals(2, entityHandler.getEntities().size());
    }

    @Test
    void testWeaponFoeCollision() {
        float delta = .02f;
        Entity weapon = new MockWeapon(new Rectangle(300, 300, 10, 10));
        entityHandler.spawnEntity(weapon);
        Entity foe = new MockFoe(new Rectangle(300, 300, 10, 10));
        entityHandler.spawnEntity(foe);
        entityHandler.update(delta, true);
        // Weapon and foe killed by collision with each other
        assertEquals(1, entityHandler.getEntities().size());
    }

    @Test
    void testPlayerItemCollision() {
        float delta = .02f;
        Entity item = new MockItem(entityHandler.getPlayerHitBox(), null);
        entityHandler.spawnEntity(item);
        entityHandler.update(delta, true);
        // Item removed when colliding with player
        assertEquals(1, entityHandler.getEntities().size());
    }

    @Test
    void testPlayerFoeCollision() {
        float delta = .02f;
        Entity foe = new MockFoe(entityHandler.getPlayerHitBox());
        entityHandler.spawnEntity(foe);
        entityHandler.update(delta, true);
        // Foe removed when colliding with player and game over
        assertEquals(1, entityHandler.getEntities().size());
        verify(mockGameWorld).onGameOver();
    }

    @Test
    void testPlayerTimedFoeCollision() {
        float delta = .02f;
        Entity foe = new MockTimedFoe(entityHandler.getPlayerHitBox());
        entityHandler.oneUp();
        entityHandler.spawnEntity(foe);
        entityHandler.update(delta, true);
        verify(mockGameWorld, never()).onGameOver();
        entityHandler.update(delta, true);
        // Foe remains when colliding with player
        assertEquals(2, entityHandler.getEntities().size());
        verify(mockGameWorld).onGameOver();
    }

    @Test
    void testBulletAddedWhenShoot() {
        float delta = .02f;
        Entity item = new MockItem(entityHandler.getPlayerHitBox(), ItemEffect.GUN);
        entityHandler.spawnEntity(item);
        entityHandler.update(delta, true);
        assertEquals(1, entityHandler.getEntities().size());
        entityHandler.shoot();
        assertEquals(2, entityHandler.getEntities().size());
        assertEquals(Constants.BULLET_KEY, entityHandler.getEntities().get(1).getKey());
    }

    @Test
    void testPunchEntityAddedWhenPunch() {
        float delta = .02f;
        Entity item = new MockItem(entityHandler.getPlayerHitBox(), ItemEffect.PUNCH);
        entityHandler.spawnEntity(item);
        entityHandler.update(delta, true);
        assertEquals(1, entityHandler.getEntities().size());
        entityHandler.punch();
        assertEquals(2, entityHandler.getEntities().size());
        assertEquals(Constants.PUNCH_ENTITY_KEY, entityHandler.getEntities().get(1).getKey());
    }

    class MockWeapon extends Entity {

        public MockWeapon(Rectangle drawBox) {
            this.drawBox = drawBox;
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public void update(float delta, EntityGameWorld gameWorld) {
            // Do nothing
        }

        @Override
        public EntityType getType() {
            return EntityType.PLAYER_WEAPON;
        }
    }

    class MockFoe extends Foe {

        public MockFoe(Rectangle drawBox) {
            this.drawBox = drawBox;
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public void update(float delta, EntityGameWorld gameWorld) {
            // Do nothing
        }
    }

    class MockItem extends Item {

        private ItemEffect effect;

        public MockItem(Rectangle drawBox, ItemEffect effect) {
            this.drawBox = drawBox;
            this.effect = effect;
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public ItemEffect getEffect() {
            return effect;
        }

        @Override
        public char getEffectKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getEffectKey'");
        }

    }

    class MockTimedFoe extends TimedFoe {

        public MockTimedFoe(Rectangle drawBox) {
            this.drawBox = drawBox;
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public void update(float delta, EntityGameWorld gameWorld) {
            // Do nothing
        }
    }
}
