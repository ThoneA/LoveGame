package app.view.menuview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import app.model.score.Scoreboard;
import java.util.List;

/**
 * MenuView manages the main menu UI in a game, providing methods to display
 * the menu with options like start, help, high scores, and quit.
 */
public class MenuView {

    private Stage stage;
    private Skin skin;
    private TextButton startButton, helpButton, highscoresButton, quitButton;

    /**
     * Constructs a new MenuView with a specified stage.
     * The menu uses a predefined skin for its UI components.
     *
     * @param stage the stage where the menu will be displayed.
     */
    public MenuView(Stage stage) {
        this.stage = stage;
        this.skin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
    }

    /**
     * Displays the main menu with options to start the game, access help,
     * view high scores, or quit the game.
     * 
     * @param onStartGame A {@link Runnable} to be executed when the "Start" button
     *                    is pressed.
     */
    public void showMenu(Runnable onStartGame) {
        Dialog dialog = new Dialog("", skin);
        Table table = setupTable();

        // Set listeners for buttons
        setupButtonListeners(onStartGame, dialog);

        // Configure dialog properties
        dialog.add(table).width(400).height(300).expand().fill();
        dialog.pack();
        dialog.setPosition((stage.getWidth() - dialog.getWidth()) / 2, (stage.getHeight() - dialog.getHeight()) / 2);
        dialog.show(stage);
    }

    private Table setupTable() {
        Label titleLabel = createTitleLabel();
        startButton = new TextButton("Start", skin);
        helpButton = new TextButton("Help", skin);
        highscoresButton = new TextButton("Highscores", skin);
        quitButton = new TextButton("Quit", skin);

        Table table = new Table(skin);
        table.add(titleLabel).expandX().fillX().center().pad(10).row();
        table.add(startButton).fillX().uniformX().pad(5).row();
        table.add(helpButton).fillX().uniformX().pad(5).row();
        table.add(highscoresButton).fillX().uniformX().pad(5).row();
        table.add(quitButton).fillX().uniformX().pad(5).row();
        return table;
    }

    private Label createTitleLabel() {
        Label titleLabel = new Label("Welcome to the Game!", skin, "default");
        titleLabel.setWrap(true);
        titleLabel.setAlignment(Align.center);
        return titleLabel;
    }

    /**
     * Sets up listeners for the menu buttons.
     * 
     * @param onStartGame
     * @param dialog
     */
    private void setupButtonListeners(Runnable onStartGame, Dialog dialog) {
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onStartGame.run();
                dialog.hide();
            }
        });
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showHelpDialog();
            }
        });
        highscoresButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showHighScoresDialog();
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void showHighScoresDialog() {
        List<Integer> topScores = Scoreboard.getScoreboard();
        Dialog highScoresDialog = new Dialog("Highscores", skin);
        StringBuilder scoreText = new StringBuilder();
        for (int i = 0; i < Math.min(topScores.size(), 5); i++) {
            scoreText.append((i + 1) + ". " + topScores.get(i) + "\n");
        }
        highScoresDialog.text(scoreText.toString());
        highScoresDialog.button("OK");
        highScoresDialog.show(stage);
    }

    private void showHelpDialog() {
        Dialog helpDialog = new Dialog("How to Play", skin);
        helpDialog.text("Instructions:\n\n" +
                "- Do not get burned and die\n" +
                "- Press the space key to fly\n" +
                "- Press the ESC button to pause the game\n" +
                "- Pick up hearts to shoot with the F button the next 5 seconds\n" +
                "- Pick up apples to punch with the D button the next 5 seconds\n" +
                "- Pick up stars to be invincible for the next 5 seconds\n" +
                "- Pick up bananas to get extra health");
        helpDialog.button("OK");
        helpDialog.key(Input.Keys.ENTER, null);
        helpDialog.show(stage);
    }

    /**
     * Gets the stage of the stage.
     * 
     * @return stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Gets the skin of the menu.
     * 
     * @return skin
     */
    public Skin getSkin() {
        return this.skin;
    }
}
