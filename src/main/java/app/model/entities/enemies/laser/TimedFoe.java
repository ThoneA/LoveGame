package app.model.entities.enemies.laser;

import app.model.entities.EntityType;
import app.model.entities.TimedEntity;

/**
 * A timed entity with the TIMED_FOE type.
 */
public abstract class TimedFoe extends TimedEntity {

    @Override
    public EntityType getType() {
        return EntityType.TIMED_FOE;
    }

}
