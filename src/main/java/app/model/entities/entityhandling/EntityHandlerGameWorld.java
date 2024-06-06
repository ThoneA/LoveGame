package app.model.entities.entityhandling;

import app.model.score.ScoreEvent;

/**
 * This interface is used by the entity handler to get necessary information
 * from the gameworld and also notify the gameworld when events happen.
 */
public interface EntityHandlerGameWorld {

    /**
     * Gets the current score
     * 
     * @return score
     */
    public int getScore();

    /**
     * Gets the current level
     * 
     * @return level
     */
    public int getLevel();

    /**
     * Handles game over.
     */
    public void onGameOver();

    /**
     * Increases the score based on the given event.
     * 
     * @param event
     */
    public void postScoreEvent(ScoreEvent event);
}
