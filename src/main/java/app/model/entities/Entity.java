package app.model.entities;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.entityhandling.EntityGameWorld;
import app.view.gameview.ViewableEntity;

/**
 * Contains general methods and fields that all entities should have.
 */
public abstract class Entity implements ViewableEntity {

    protected Rectangle drawBox;
    protected boolean alive = true;

    /**
     * Get the hitbox of the entity. With x and y coordinates, and width and
     * height. Returns a copy of the hitBox as a new Rectangle object.
     * 
     * @return hit box of the entity
     */
    public Rectangle getHitBox() {
        return new Rectangle(drawBox);
    }

    @Override
    public Rectangle getDrawBox() {
        return new Rectangle(drawBox);
    }

    /**
     * Returns true if the hit boxes of two entities overlap.
     * 
     * @param entity
     * @return true if entities is coliding
     */
    public boolean isColliding(Entity entity) {
        return getHitBox().overlaps(entity.getHitBox());
    }

    /**
     * Kills the entity.
     */
    public void kill() {
        alive = false;
    }

    /**
     * Checks if the entity is alive.
     * 
     * @return true if entity is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Updates the position and/or state of the entity.
     */
    public abstract void update(float delta, EntityGameWorld gameWorld);

    /**
     * Gets the type of the entity.
     * 
     * @return type
     */
    public abstract EntityType getType();

}
