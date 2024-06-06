package app.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import app.model.GameState;
import app.model.GameWorld;
import app.utils.Constants;
import app.view.gameview.GameView;
import app.view.menuview.GameWorldListener;
import app.view.menuview.LibGDXToggleBox;
import app.view.menuview.MenuView;

/**
 * This class represents the core of the Endless Runner game.
 * It initializes the game world and contains the main game loop.
 * It extends ApplicationAdapter, making it the primary class that LibGDX
 * uses to manage the game lifecycle (initialize, render, dispose, etc.).
 * It acts as the bridge between the LibGDX framework and the game logic.
 */
public class EndlessRunnerGame extends ApplicationAdapter implements GameWorldListener {
    private SpriteBatch batch;
    private GameWorld gameWorld;
    private GameView gameView;
    private Stage stage;
    private FitViewport viewPort = new FitViewport(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
    private MenuView menuView;
    private LibGDXToggleBox gameToggleBox;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameWorld = new GameWorld(this);
        gameView = new GameView(gameWorld);

        stage = new Stage(new ScreenViewport());
        gameToggleBox = new LibGDXToggleBox(stage);

        // Setup MenuView and display the menu at the start of the game.
        // The Runnable parameter will contain logic to transition from the menu to the
        // game.
        this.menuView = new MenuView(stage); // Assign the instance to the class field
        menuView.showMenu(new Runnable() {
            @Override
            public void run() {
                gameWorld.runGame();
            }
        });

        setupInputProcessor();
    }

    @Override
    public void showMenu() {
        Gdx.app.postRunnable(() -> {
            stage.clear(); // Clear the stage to remove game-specific actors
            menuView.showMenu(() -> {
                gameWorld.runGame();
            });
            setupInputProcessor();
        });
    }

    /**
     * Encapsulates the logic for setting up the input processor.
     * It's called from create(), keeping the setup process organized
     * and create() focused on initialization tasks.
     */
    private void setupInputProcessor() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        // Add the stage first, to ensure UI elements receive input events before the
        // game logic
        inputMultiplexer.addProcessor(stage);

        // custom input processor
        inputMultiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.ESCAPE:
                        togglePause();
                        break;
                    case Input.Keys.SPACE:
                        gameWorld.ascend();
                        break;
                    case Input.Keys.F:
                        gameWorld.shoot();
                        break;
                    case Input.Keys.D:
                        gameWorld.punch();
                        break;
                    default:
                        return false; // Important to return false if not handled
                }
                return true; // Only return true if the event was handled
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        gameWorld.fall();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

        // Set the InputMultiplexer as the input processor
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * This method abstracts the logic for toggling the game's pause state.
     * It checks the current state and calls either pauseGame() or resumeGame()
     * on gameWorld.
     */
    private void togglePause() {
        GameState gameState = gameWorld.getGameState();
        if (gameState == GameState.RUNNING) {
            gameWorld.pauseGame();
        } else if (gameState == GameState.PAUSED) {
            gameWorld.runGame();
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (gameWorld.getGameState() != GameState.MENU) {
            // Only update and render the game world if the game is running
            gameWorld.update(Gdx.graphics.getDeltaTime());
            gameView.render(batch);
        }

        // Always process and draw the stage for UI elements like the menu
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        if (batch != null)
            batch.dispose();
        if (stage != null)
            stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false); // this was changed to false
        viewPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void onGamePaused() {
        showPauseMenu();
    }

    private void showPauseMenu() {
        Gdx.app.postRunnable(() -> {
            // Assuming gameToggleBox is initialized with the stage and skin
            gameToggleBox.display(
                    "Game Paused. Click OK to resume or Back to Menu to exit.",
                    () -> gameWorld.runGame(), // Runnable for resume
                    () -> {
                        showMainMenu(); // Logic to return to the main menu
                        gameWorld.menu();
                    });
        });
    }

    private void showMainMenu() {
        Gdx.app.postRunnable(() -> {
            // Clear any game-specific UI elements or actors added to the stage
            stage.clear();

            // Show the main menu again
            menuView.showMenu(() -> {
                setupInputProcessor();
                gameWorld.runGame(); // Logic to start the game, similar to your existing setup
            });

            // Make sure the input processor is set to the stage to capture UI interactions
            setupInputProcessor();
        });
    }

    @Override
    public void onGameReset() {
        showMainMenu();
    }

    @Override
    public void onGameOver() {
        Gdx.app.postRunnable(() -> {
            // Clear any gameplay-specific UI elements
            stage.clear();

            // Show the game over dialog
            gameToggleBox.display(
                    "Game Over! Your score: " + gameWorld.getScore() + ". Click OK to restart.",
                    this::restartGame);

            // Reset input processor to the stage to capture dialog interactions
            Gdx.input.setInputProcessor(stage);
        });
    }

    private void restartGame() {
        setupInputProcessor();
        gameWorld.resetGame();

    }

}
