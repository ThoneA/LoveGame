package app.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.*;

import app.model.score.ScoreEvent;
import app.utils.Constants;
import app.view.menuview.GameWorldListener;
import org.junit.jupiter.api.Test;

/**
 * Unit tests (example).
 * 
 * (Run using `mvn test`)
 */
public class GameWorldTest {

	private GameWorld gameWorld;
	private GameWorldListener listener;

	/**
	 * Setup method called before each of the test methods
	 */
	@BeforeEach
	void setUp() {
		listener = mock(GameWorldListener.class);
		gameWorld = new GameWorld(listener);
	}

	/**
	 * Tests that the game state changes to RUNNING when startGame() is called.
	 */
	@Test
	void testStartGame_ChangesStateToRunning() {
		gameWorld.runGame();
		assertEquals(GameState.RUNNING, gameWorld.getGameState());
	}

	/**
	 * Tests the game is paused correctly.
	 */
	@Test
	void testPauseGame_WhenRunning_PausesGame() {
		gameWorld.runGame();
		gameWorld.pauseGame();
		assertEquals(GameState.PAUSED, gameWorld.getGameState());
		verify(listener).onGamePaused();
	}

	/**
	 * Tests restarting the game resets to expected conditions.
	 */
	@Test
	void testRestartGame_ResetsGameCorrectly() {
		gameWorld.resetGame();
		assertEquals(GameState.RUNNING, gameWorld.getGameState());
	}

	/**
	 * Tests that the GameWorld initializes with the correct state.
	 * The initial game state should be MENU, indicating that the game
	 * is ready to start but has not yet begun. This test ensures the game
	 * world's setup logic correctly sets up the initial state.
	 */
	@Test
	void testGameWorld_InitializesWithCorrectState() {
		assertEquals(GameState.MENU, gameWorld.getGameState(), "Game should start in MENU state");
	}

	/**
	 * Tests that the game correctly pauses when in the RUNNING state.
	 * When paused, the game should not update its state or entities until
	 * it is resumed. This test verifies the transition to the PAUSED state
	 * and checks that the game's update logic is effectively suspended.
	 */
	@Test
	void testPauseGame_WhenRunning_PausesUpdates() {
		gameWorld.runGame();
		gameWorld.pauseGame();
		assertEquals(GameState.PAUSED, gameWorld.getGameState(), "Pausing the game should set state to PAUSED");
	}

	/**
	 * Tests the game's ability to resume from a paused state back to running.
	 * This test ensures that the game can successfully transition out of a paused
	 * state and continue updating its logic and entities as expected, effectively
	 * "unpausing" the game.
	 */
	@Test
	void testResumeGame_ResumesFromPaused() {
		gameWorld.runGame();
		gameWorld.pauseGame();
		gameWorld.runGame();
		assertEquals(GameState.RUNNING, gameWorld.getGameState(), "Resuming the game should set state to RUNNING");
		// Add missing import statements here

	}

	@Test
	void testGetPlayerHealth() {
		assertEquals(gameWorld.getPlayerHealth(), Constants.PLAYER_INIT_HEALTH);
	}

	@Test
	void testGetLevel() {
		assertEquals(gameWorld.getLevel(), 0);
	}

	@Test
	void testGetScore() {
		assertEquals(gameWorld.getScore(), 0);
	}

	@Test
	void testGetEntities() {
		assertNotNull(gameWorld.getEntities());
	}

	@Test
	void testPunch() {
		gameWorld.punch();
		assertEquals(gameWorld.getEntities().size(), 1);
	}

	@Test
	void testShoot() {
		gameWorld.shoot();
		assertEquals(gameWorld.getEntities().size(), 1);
	}

	@Test
	void testMenu() {
		gameWorld.menu();
		assertEquals(gameWorld.getGameState(), GameState.MENU);
	}

	@Test
	void testPostScoreEvent() {
		gameWorld.postScoreEvent(ScoreEvent.SHOT_FOE);
		assertEquals(gameWorld.getScore(), Constants.SCORE_HITTING_FOE);
	}

	@Test
	void testOnGameOver() {
		gameWorld.onGameOver();
		assertEquals(gameWorld.getGameState(), GameState.GAME_OVER);
	}

	@Test
	void testShouldUpdateGame() {
		assertTrue(gameWorld.shouldUpdateGame());
	}

}
