package app.model.entities.items;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

import java.util.Random;

/**
 * Abstract class for item-entities. Controls the movement and spawning points,
 * and duration of item. Does not contain the effect or key.
 */
public abstract class Item extends Entity {

    protected float timer = Constants.ITEM_DURATION;

    /**
     * Creates a new item at a random height to the right of the board.
     */
    public Item() {
        int size = Constants.ITEM_SIZE;
        int x = Constants.BOARD_WIDTH + size;
        int heightLimit = Constants.BOARD_HEIGHT - size;
        Random rnd = new Random();
        int y = rnd.nextInt(heightLimit);
        this.drawBox = new Rectangle(x, y, size, size);
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        drawBox.x -= Constants.ITEM_SPEED * delta;
    }

    @Override
    public EntityType getType() {
        return EntityType.ITEM;
    }

    /**
     * Gets the item effect
     * 
     * @return effect
     */
    public abstract ItemEffect getEffect();

    /**
     * Updates the timer for the duration of the item.
     */
    public void updateTimer(float delta) {
        timer -= delta;
    }

    /**
     * Is true if the duration of the effect is over.
     * 
     * @return effect finished
     */
    public boolean effectOver() {
        return timer <= 0;
    }

    /**
     * Chooses how it affects the game world when item is added.
     * By default does nothing.
     */
    public void onAdd(EntityGameWorld gameWorld) {

    }

    /**
     * Gets the key of the effect of the item, used to draw it.
     * 
     * @return effect key
     */
    public abstract char getEffectKey();
}
