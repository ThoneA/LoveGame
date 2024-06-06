package app.model;

import java.util.List;

import app.model.entities.entityhandling.EntityHandler;
import app.model.entities.entityhandling.EntityHandlerGameWorld;
import app.model.score.*;
import app.utils.Constants;
import app.view.gameview.ViewableEntity;
import app.view.gameview.ViewableGameWorld;
import app.view.menuview.GameWorldListener;

/**
 * Manages the game environment for the Endless Runner game.
 * Handles the foes, items and player entity.
 * 
 * Role:
 * This class is designed to encapsulate the "state" and logic of the game
 * world. It doesn't directly interact with the LibGDX lifecycle methods but is
 * instead called by EndlessRunnerGame.
 * 
 * Responsibilities:
 * Game Logic (update method): Contains the logic to update the game state, such
 * as moving the player and obstacles based on time (delta). This method should
 * be called from the render method of EndlessRunnerGame.
 *
 */
public class GameWorld implements ViewableGameWorld, EntityHandlerGameWorld {

    private GameState gameState = GameState.MENU;
    private Score score = new Score();
    private Scoreboard scoreboard = new Scoreboard();
    private GameWorldListener listener;
    private EntityHandler entityHandler;

    /**
     * Initializes the game world.
     * 
     * @param listener
     */
    public GameWorld(GameWorldListener listener) {
        this.listener = listener;
        this.entityHandler = new EntityHandler(this);
    }

    /**
     * Updates the game world state, including all entities.
     */
    public void update(float delta) {
        if (!shouldUpdateGame()) {
            return;
        }
        entityHandler.update(delta, gameState != GameState.GAME_OVER);
        if (gameState == GameState.GAME_OVER) {
            return; 
        }
        score.increaseScore(delta);
    }

    /**
     * Pauses the game, stopping all updates and rendering.
     */
    public void pauseGame() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED;
            if (listener != null) {
                listener.onGamePaused(); // Notify the controller of the pause event
            }
        }
    }

    /**
     * Sets the game state to menu.
     */
    public void menu() {
        resetGame();
        gameState = GameState.MENU;
    }

    /**
     * Resumes the game from a paused state.
     */
    public void runGame() {
        gameState = GameState.RUNNING;
    }

    /**
     * Resets the state of the game.
     */
    public void resetGame() {
        gameState = GameState.RUNNING;
        entityHandler.reset();
        score.reset();
    }

    /**
     * Gives the state that the game is currently in.
     * 
     * @return current game state
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * Changes the direction of the player to move upwards.
     */
    public void ascend() {
        if (gameState != GameState.GAME_OVER) {
            entityHandler.ascend();
        }
    }

    /**
     * Changes the direction of the player to move downwards.
     */
    public void fall() {
        entityHandler.fall();
    }

    /**
     * Spawns a bullet from the right side of the player if the player has a gun.
     */
    public void shoot() {
        entityHandler.shoot();
    }

    /**
     * Makes the player punch and kill foes if the player has a punch item.
     */
    public void punch() {
        entityHandler.punch();
    }

    @Override
    public boolean shouldUpdateGame() {
        boolean paused = gameState == GameState.PAUSED;
        boolean gameOver = gameState == GameState.GAME_OVER;
        boolean playerOnGround = entityHandler.isPlayerStandingOnTheGround();
        boolean noObstacles = entityHandler.isEmpty();
        return !(paused || (gameOver && playerOnGround && noObstacles));
    }

    @Override
    public List<ViewableEntity> getEntities() {
        return entityHandler.getEntities();
    }

    @Override
    public void onGameOver() {
        if (gameState != GameState.GAME_OVER) {
            gameState = GameState.GAME_OVER;
            fall();
            scoreboard.addScore(score.getScore());
            if (listener != null) {
                listener.onGameOver(); // Notify the controller.
            }
        }
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public int getLevel() {
        return score.getLevel();
    }

    @Override
    public void postScoreEvent(ScoreEvent event) {
        switch (event) {
            case SHOT_FOE:
                score.increaseScore(Constants.SCORE_HITTING_FOE);
                break;
            default:
                throw new IllegalArgumentException("The event '" + event + "' was not handled");
        }
    }

    @Override
    public int getPlayerHealth() {
        return entityHandler.getPlayerHealth();
    }

}
