package app.model.scoreboard;

import app.model.score.Scoreboard;
import app.utils.Constants;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardTest {

    private Scoreboard scoreboard;
    private File testFile;
    private static StringBuilder storedData;

    @BeforeEach
    void setUp() throws IOException {
        testFile = new File(Constants.SCORE_FILE);
        testFile.delete();
        scoreboard = new Scoreboard();
    }

    @BeforeAll
    static void storeUserData() throws IOException {
        File file = new File(Constants.SCORE_FILE);
        if (file.createNewFile()) {
            storedData = null;
        } else {
            storedData = new StringBuilder();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                storedData.append(reader.nextLine() + "\n");
            }
            reader.close();
        }
    }

    @AfterAll
    static void reviveUserData() throws IOException {
        File file = new File(Constants.SCORE_FILE);
        if (storedData != null) {
            FileWriter writer = new FileWriter(file);
            writer.write(storedData.toString());
            writer.close();
        } else {
            file.delete();
        }
    }

    @Test
    void testAddingNewHighScore() {
        boolean isAdded = scoreboard.addScore(100);
        assertTrue(isAdded, "Score should be added");
        List<Integer> scores = Scoreboard.getScoreboard();
        assertEquals(Integer.valueOf(100), scores.get(0), "The high score should be at the top");
    }

    @Test
    void testAddingZeroAndNegativeScores() {
        assertFalse(scoreboard.addScore(0), "Zero score should not be added");
        assertFalse(scoreboard.addScore(-10), "Negative score should not be added");
    }

    @Test
    void testScoreboardOrder() {
        scoreboard.addScore(200);
        scoreboard.addScore(100);
        scoreboard.addScore(300);
        List<Integer> scores = Scoreboard.getScoreboard();
        assertEquals(Integer.valueOf(300), scores.get(0), "Scoreboard should be in descending order");
        assertEquals(Integer.valueOf(200), scores.get(1), "Scoreboard should be in descending order");
        assertEquals(Integer.valueOf(100), scores.get(2), "Scoreboard should be in descending order");
    }

    @Test
    void testScoreboardPerformance() {
        long startTime = System.nanoTime();
        for (int i = 0; i < Constants.SCOREBOARD_SIZE; i++) {
            scoreboard.addScore((int) (Math.random() * 1000));
        }
        long endTime = System.nanoTime();
        assertTrue((endTime - startTime) < 1_000_000_000, "Performance: Adding scores should be fast");
    }

}
