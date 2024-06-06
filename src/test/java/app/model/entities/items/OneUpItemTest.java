package app.model.entities.items;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class OneUpItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new OneUpItem();
    }

    @Test
    void testOnAdd() {
        EntityGameWorld gameWorld = mock(EntityGameWorld.class);
        item.onAdd(gameWorld);
        verify(gameWorld, times(1)).oneUp();
    }

    @Test
    void testGetEffectKey() {
        assertEquals(Constants.ONE_UP_EFFECT_KEY, item.getEffectKey());
    }

    @Test
    void testGetEffect() {
        assertEquals(ItemEffect.ONE_UP, item.getEffect());
    }

    @Test
    void testGetKey() {
        assertEquals(Constants.ONE_UP_ITEM_KEY, item.getKey());
    }

}
