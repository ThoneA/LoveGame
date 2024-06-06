package app.model.entities.player;

import com.badlogic.gdx.math.Rectangle;

import app.utils.Constants;
import app.model.entities.EntityType;
import app.model.entities.items.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
    }

    @Test
    void testPlayerPositionInitialization() {
        Rectangle hitBox = new Rectangle(
                Constants.PLAYER_STARTING_POSITION.x,
                Constants.PLAYER_STARTING_POSITION.y,
                Constants.PLAYER_WIDTH,
                Constants.PLAYER_HEIGHT);
        assertEquals(hitBox, player.getDrawBox());
    }

    @Test
    void testPlayerBounds() {
        player.ascend();
        player.update(100, null);
        Rectangle drawBox = player.getDrawBox();
        assertEquals(drawBox.y, Constants.BOARD_HEIGHT - drawBox.height, 0.1);
        assertEquals(Constants.PLAYER_STARTING_POSITION.x, drawBox.x, 0.1);
        player.fall();
        player.update(100, null);
        drawBox = player.getDrawBox();
        assertEquals(drawBox.y, 0, 0.1);
        assertEquals(Constants.PLAYER_STARTING_POSITION.x, drawBox.x, 0.1);
    }

    @Test
    void isStandingOnTheGroundInitiallyTest() {
        assertTrue(player.isStandingOnTheGround());
    }

    @Test
    void testAscendNotOnTheGround() {
        player.ascend();
        player.update(1, null);
        assertFalse(player.isStandingOnTheGround());
    }

    @Test
    void testUpdateWithGravity() {
        player.ascend();
        player.update(1, null);
        float initialY = player.getHitBox().y;
        player.fall();
        player.update(1, null);
        assertTrue(player.getHitBox().y < initialY);
    }

    @Test
    void getKeyTest() {
        assertEquals(Constants.PLAYER_TEXTURE_KEY, player.getKey());
    }

    @Test
    void testIsMovingUp() {
        player.ascend();
        assertTrue(player.isMovingUp());
        player.fall();
        assertFalse(player.isMovingUp());
    }

    @Test
    void testMovingSlowerWhenAscendingFromAir() {
        float delta = .25f;
        float prevY = player.getDrawBox().y;
        player.ascend();
        player.update(delta, null);
        float expectedGreaterDiff = player.getDrawBox().y - prevY;
        prevY = player.getDrawBox().y;
        player.fall();
        player.ascend();
        player.update(delta, null);
        float expectedSmallerDiff = player.getDrawBox().y - prevY;
        assertTrue(expectedGreaterDiff > expectedSmallerDiff);
    }

    @Test
    void testGetType() {
        assertEquals(EntityType.PLAYER, player.getType());
    }

    @Test
    void testItems() {
        Item item1 = new PunchItem();
        Item item2 = new InvincibleItem();
        Item item3 = new ShootingItem();

        assertFalse(player.hasEffect(item2.getEffect()));
        player.addItem(item1);
        player.addItem(item2);
        assertTrue(player.hasEffect(item2.getEffect()));
        assertTrue(player.getEffects().contains(item1.getEffectKey()));
        assertFalse(player.getEffects().contains(item3.getEffectKey()));

        float delta = .1f;
        player.update(delta, null);
        assertTrue(player.hasEffect(item1.getEffect()));
        player.update(Constants.ITEM_DURATION, null);
        assertFalse(player.hasEffect(item1.getEffect()));
    }   
}
