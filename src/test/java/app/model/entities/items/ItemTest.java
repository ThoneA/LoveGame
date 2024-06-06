package app.model.entities.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Rectangle;

import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

import app.model.entities.EntityType;
import app.model.entities.entityhandling.EntityGameWorld;
import static org.mockito.Mockito.mock;

public class ItemTest {

    private Item item;

    class TestItem extends Item {

        TestItem() {
            this.drawBox = new Rectangle(0,0,100,100);
        }

        @Override
        public char getKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getKey'");
        }

        @Override
        public ItemEffect getEffect() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getEffect'");
        }

        @Override
        public char getEffectKey() {
            // Implementation not needed for testing purposes
            throw new UnsupportedOperationException("Unimplemented method 'getEffectKey'");
        }
        
    }

    @BeforeEach
    void setUp() {
        item = new TestItem();
    }
    
    @Test
    void testEffectOverInitialValue() {
        assertTrue(!item.effectOver());
    }
    
    @Test
    void testEffectOverTime() {
        float margin = .1f;
        item.updateTimer(Constants.ITEM_DURATION - margin);
        assertTrue(!item.effectOver());
        item.updateTimer(margin);
        assertTrue(item.effectOver());
    }

    @Test
    void testUpdate() {
        EntityGameWorld gameWorld = mock(EntityGameWorld.class);
        float delta = .1f; 
        float initX = item.getDrawBox().x; 
        item.update(delta, gameWorld);
        float expectedX = initX - Constants.ITEM_SPEED * delta;
        assertEquals(expectedX, item.getDrawBox().x); 
    }

    @Test
    void testgetType() {
        assertEquals(item.getType(), EntityType.ITEM);
    }

    @Test
    void testOnAdd() {
        assertDoesNotThrow(() -> item.onAdd(null));
    }

}
