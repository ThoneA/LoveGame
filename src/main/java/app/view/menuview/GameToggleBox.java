package app.view.menuview;

/**
 * Interface for displaying a toggle box in the game within LibGDX restrictions.
 */
public interface GameToggleBox {

    /**
     * Displays a toggle box with the given message.
     * @param message
     * @param onConfirm
     * @param onBackToMenu
     */
    void display(String message, Runnable onConfirm, Runnable onBackToMenu);

    /**
     * Displays a toggle box with the given message.
     * @param message
     * @param onConfirm
     */
    void display(String message, Runnable onConfirm);
}
