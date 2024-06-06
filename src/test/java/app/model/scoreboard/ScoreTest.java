package app.model.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.model.score.Score;
import app.utils.Constants;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score();
    }

    @Test
    void testInitialScoreIsZero() {
        assertEquals(0, score.getScore(), "Initial score should be zero");
    }

    @Test
    void testIncreaseScoreByDelta() {
        score.increaseScore(0.5f);
        assertEquals(0, score.getScore(), "Score should not increase until delta reaches 1");
        score.increaseScore(0.6f);
        assertEquals(1, score.getScore(), "Score should increase by 1 when delta reaches 1");
    }

    @Test
    void testIncreaseScoreThrowsExceptionForLargeDelta() {
        assertThrows(IllegalArgumentException.class, () -> score.increaseScore(101),
                "Exception should be thrown for delta greater than 100");
    }

    @Test
    void testScoreIncreasesCorrectlyWithMultipleDeltas() {
        score.increaseScore(0.2f);
        score.increaseScore(0.5f);
        assertEquals(0, score.getScore(), "Score should not increase until delta reaches 1");
        score.increaseScore(0.6f);
        assertEquals(1, score.getScore(), "Score should increase by 1 when delta reaches 1");
    }

    @Test
    void testResetScore() {
        score.increaseScore(3);
        score.reset();
        assertEquals(0, score.getScore(), "Score should be reset to zero");
        assertEquals(0, score.getLevel(), "Level should be reset to zero");
    }

    @Test
    void levelTest() {
        int prevLevel = 0;
        for (int i : Constants.SCORE_TO_LEVEL_TABLE) {
            score.increaseScore(i - score.getScore());
            assertEquals(++prevLevel, score.getLevel());
        }
    }
}