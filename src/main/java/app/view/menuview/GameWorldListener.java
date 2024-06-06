package app.view.menuview;
/**
 * The {@code GameWorldListener} interface provides a set of callbacks
 * for responding to key game events. It is used to communicate changes
 * in the game state from the game model to external systems such as controllers
 * or views, which can then react appropriately. This design facilitates a
 * separation of concerns by allowing the game model to remain independent
 * of the specific actions taken when these events occur.
 */
public interface GameWorldListener {

    /**
     * Invoked to indicate that the main menu should be displayed.
     * Implementers should define the specific actions to take when
     * this request is made, such as showing or updating UI elements.
     */
    void showMenu();

    /**
     * Called when the game is paused.
     * This method provides a hook for pausing the game's updates and notifying
     * external systems to reflect the paused state, possibly through a UI update.
     */
    void onGamePaused();

    /**
     * Called when the game needs to be reset to its initial state.
     * This method provides a hook for reinitializing game elements and notifying
     * external systems to prepare for a new game start.
     */
    void onGameReset();

    /**
     * Called when the game has concluded.
     * This method should trigger any necessary cleanup and updates to external systems,
     * such as displaying final scores or game over screens, and handling game session cleanup.
     */
    void onGameOver();
}
