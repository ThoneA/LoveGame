package app.model.entities.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.utils.Constants;

public class ShootingItemTest {

    private Item item;
    
    @BeforeEach
    void setUp() {
        item = new ShootingItem();
    }
    
    @Test
    void testGetEffectKey() {
        assertEquals(Constants.GUN_EFFECT_KEY, item.getEffectKey());
    }

    @Test
    void testGetEffect() {
        assertEquals(ItemEffect.GUN, item.getEffect());
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.SHOOTING_ITEM_KEY, item.getKey());
    }
}
