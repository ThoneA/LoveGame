package app.model.score;

import app.utils.Constants;

/**
 * Keeps track of the in-game score and level.
 */
public class Score {

    private int score;
    private float timeSincePoint;
    private int level;

    /**
     * Intializes the score.
     */
    public Score() {
        reset();
    }

    /**
     * Increases the score based on the given delta.
     * 
     * @param delta
     * @throw IllegalArgumentException if delta is greater than 100
     */
    public void increaseScore(float delta) {
        if (delta > 100) {
            throw new IllegalArgumentException("The given delta was '" + delta + "', the delta can't exceed 100.");
        }
        timeSincePoint += delta;
        while (timeSincePoint >= 1) {
            this.score += 1;
            timeSincePoint -= 1;
            updateLevel();
        }
    }

    /**
     * Gets the score.
     * 
     * @return score
     */

    public int getScore() {
        return this.score;
    }

    /**
     * Resets the score.
     */
    public void reset() {
        score = 0;
        timeSincePoint = 0;
        level = 0;
    }

    /**
     * Gets the current level based on the score
     * 
     * @return level
     */
    public int getLevel() {
        return level;
    }

    private void updateLevel() {
        int[] levelTable = Constants.SCORE_TO_LEVEL_TABLE;
        if (level < levelTable.length && score >= levelTable[level]) {
            level++;
        }
    }

}
