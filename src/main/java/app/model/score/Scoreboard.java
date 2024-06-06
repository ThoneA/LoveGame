package app.model.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.badlogic.gdx.utils.StringBuilder;

import app.utils.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;

/**
 * Keeps track of and adds new scores to the scoreboard.
 */
public class Scoreboard {

    private static List<Integer> scores;
    private File file;

    /**
     * Loads the scoreboard from a file if it exists or creates a new scoreboard if
     * it does not exist.
     */
    public Scoreboard() {
        scores = new LinkedList<>();
        initializeScoreboard();
    }

    private void initializeScoreboard() {
        try {
            this.file = new File(Constants.SCORE_FILE);
            if (file.createNewFile()) {
                for (int i = 0; i < Constants.SCOREBOARD_SIZE; i++) {
                    scores.add(0);
                }
                saveScoreboardToFile();
            } else {
                importScoreboard();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importScoreboard() {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                int score = Integer.parseInt(reader.nextLine());
                scores.add(score);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of all the scores on the scoreboard.
     * 
     * @return scoreboard as list
     */
    public static List<Integer> getScoreboard() {
        return new ArrayList<>(scores);
    }

    /**
     * Adds the score to the scoreboard if the scores is better than any of the
     * current best x scores, returns true accordingly.
     * 
     * @return is added to scoreboard
     */
    public boolean addScore(int score) {
        for (int i = 0; i < scores.size(); i++) {
            if (score > scores.get(i)) {
                scores.add(i, score);
                scores.remove(scores.size() - 1);
                saveScoreboardToFile();
                return true;
            }
        }
        return false;
    }

    private void saveScoreboardToFile() {
        try {
            StringBuilder scoreboardString = new StringBuilder();
            for (Integer score : scores) {
                scoreboardString.append(score + "\n");
            }
            FileWriter writer = new FileWriter(file);
            writer.write(scoreboardString.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
