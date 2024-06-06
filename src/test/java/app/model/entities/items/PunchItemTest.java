package app.model.entities.items;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.utils.Constants;

public class PunchItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new PunchItem();
    }

    @Test
    void testGetEffectKey() {
        assertEquals(Constants.PUNCH_EFFECT_KEY, item.getEffectKey());
    }

    @Test
    void testGetEffect() {
        assertEquals(ItemEffect.PUNCH, item.getEffect());
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.PUNCH_ITEM_KEY, item.getKey());
    }
}
