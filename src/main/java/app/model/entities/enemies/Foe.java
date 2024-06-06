package app.model.entities.enemies;

import app.model.entities.Entity;
import app.model.entities.EntityType;

/**
 * Abstract class for all Foe-types. Meaning all types of opponenents to the player.
 */
public abstract class Foe extends Entity {

    @Override
    public EntityType getType() {
        return EntityType.FOE;
    }
}
