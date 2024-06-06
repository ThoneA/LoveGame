package app.view.menuview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * A custom toggle box class that extends the functionality of a dialog in LibGDX.
 * This class provides a simplified interface for displaying dialogs with confirmation
 * and optional navigation options.
 */
public class LibGDXToggleBox implements GameToggleBox {

    private Stage stage;
    private Skin skin;

    /**
     * Constructs a LibGDXToggleBox with a specified stage.
     * The toggle box will use a predefined skin for its UI components.
     *
     * @param stage The stage where the dialog will be displayed, typically
     *              the main game stage.
     */
    public LibGDXToggleBox(Stage stage) {
        this.stage = stage;
        this.skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
    }

    // Overloaded method for a single Runnable (e.g., for game over)
    @Override
    public void display(String message, Runnable onConfirm) {
        display(message, onConfirm, null); // Call the two-argument version with null for the second action
    }

    // Original method, now explicitly handles null for the second Runnable
    @Override
    public void display(String message, Runnable onConfirm, Runnable onBackToMenu) {
        Dialog dialog = new Dialog("Pause Menu", skin) {
            @Override
            protected void result(Object object) {
                if ("OK".equals(object)) {
                    if (onConfirm != null) {
                        onConfirm.run();
                    }
                } else if ("Back".equals(object)) {
                    if (onBackToMenu != null) {
                        onBackToMenu.run();
                    }
                }
            }
        };
        dialog.text(message);
        dialog.button("OK", "OK"); // Pass "OK" as the identifier for the OK button
        if (onBackToMenu != null) {
            // Only add the "Back to Menu" button if there's an action provided
            dialog.button("Back to Menu", "Back");
        }
        dialog.show(stage);
    }

}
