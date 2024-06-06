package app.model.entities;

import app.model.entities.entityhandling.EntityGameWorld;

/**
 * An entity that dies after a set duration.
 */
public abstract class TimedEntity extends Entity {

    protected float duration;

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        updateDuration(delta);
    }

    private void updateDuration(float delta) {
        duration -= delta;
        if (duration <= 0) {
            kill();
        }
    }
}
