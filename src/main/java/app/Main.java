package app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import app.controller.EndlessRunnerGame;
import app.utils.Constants;

/**
 * This class serves as the entry point for launching the Endless Runner game.
 */
public class Main {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Endless Runner");
        cfg.setWindowedMode(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        
        new Lwjgl3Application(new EndlessRunnerGame(), cfg);
    }
}
 