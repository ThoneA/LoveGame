package app.model.entities.player;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.EntityType;
import app.model.entities.TimedEntity;
import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * Entity follows the players position and hits entities that is not a player.
 */
public class PunchEntity extends TimedEntity {

    /**
     * Creates a punch entity at the player's position.
     */
    public PunchEntity(EntityGameWorld gameWorld) {
        duration = Constants.PUNCH_DURATION;
        initializeDrawBox();
        updateDrawBox(gameWorld);
    }

    private void initializeDrawBox() {
        drawBox = new Rectangle();
        drawBox.width = Constants.PLAYER_WIDTH;
        drawBox.height = Constants.PLAYER_HEIGHT;
        drawBox.x = Constants.PLAYER_STARTING_POSITION.x;
    }

    private void updateDrawBox(EntityGameWorld gameWorld) {
        Rectangle playerHitBox = gameWorld.getPlayerHitBox();
        drawBox.y = playerHitBox.y;
    }

    @Override
    public char getKey() {
        return Constants.PUNCH_ENTITY_KEY;
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        super.update(delta, gameWorld);
        updateDrawBox(gameWorld);
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER_WEAPON;
    }
    
}
