package app.model.entities.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.utils.Constants;

public class InvincibleItemTest {
    
    private Item item;

    @BeforeEach
    void setUp() {
        item = new InvincibleItem();
    }

    @Test
    void testGetEffectKey() {
        assertEquals(Constants.INVINCIBLE_EFFECT_KEY, item.getEffectKey());
    }

    @Test
    void testGetEffect() {
        assertEquals(ItemEffect.INVINCIBLE, item.getEffect());
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.INVINCIBLE_ITEM_KEY, item.getKey());
    }
}
