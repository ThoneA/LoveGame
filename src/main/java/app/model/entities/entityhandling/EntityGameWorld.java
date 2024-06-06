package app.model.entities.entityhandling;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.Entity;

/**
 * The purpouse of this class is to be given as an argument to entities so that
 * they can get the information they need to update their position.
 */
public interface EntityGameWorld {

    /**
     * Gets the hit box of the player.
     * 
     * @return rectangle of player hit box
     */
    public Rectangle getPlayerHitBox();

    /**
     * Add the given entity to the game world.
     */
    public void spawnEntity(Entity entity);
    
    /**
     * Increases the player's life by 1.
     */
    public void oneUp();
}
