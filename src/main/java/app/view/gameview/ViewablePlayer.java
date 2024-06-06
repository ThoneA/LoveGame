package app.view.gameview;

import java.util.Set;

/**
 * Interface for use in GameView to access needed data for rendering the player
 * with the correct graphics.
 */
public interface ViewablePlayer extends ViewableEntity {

    /**
     * Returns true if the player is moving upwards.
     * 
     * @return true or false
     */
    public boolean isMovingUp();

    /**
     * Gets the keys of effects that the player has.
     * 
     * @return keys of effects of player
     */
    public Set<Character> getEffects();
}
