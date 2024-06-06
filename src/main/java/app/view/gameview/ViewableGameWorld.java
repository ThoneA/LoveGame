package app.view.gameview;

import java.util.List;

/**
 * Interface intended to be used for in the GameView class to restrict what it
 * can access in GameWorld.
 */
public interface ViewableGameWorld {

    /**
     * Get the list of all entities currently in the game.
     * 
     * @return list of entities
     */
    public List<ViewableEntity> getEntities();

    /**
     * Gets the score of the game.
     * 
     * @return current in-game score
     */
    public int getScore();

    /**
     * Checks if all entities, except the player, is off the map and if the game is
     * over. Should also return true if the game is paused
     * 
     * @return true if map clear and game is over or game is paused
     */
    public boolean shouldUpdateGame();

    /**
     * Gets the health of the player
     * 
     * @return player health
     */
    public int getPlayerHealth();
}
