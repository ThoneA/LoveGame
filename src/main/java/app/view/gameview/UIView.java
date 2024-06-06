package app.view.gameview;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import app.utils.Constants;

/**
 * Renders the user interface the is 
 */
public class UIView {

    /**
     * Render UI.
     * 
     * @param batch to render on
     * @param score currently in game
     * @param health of the player
     */
    public static void renderUI(SpriteBatch batch, int score, int health) {
        BitmapFont font = new BitmapFont();
        GlyphLayout glyphLayout = new GlyphLayout();
        String scoreString = "Score: " + score;
        String healthString = "Health: " + health;
        int spacingAmount = 10;
        String spacing = " ".repeat(spacingAmount);
        String uiString = scoreString + spacing + healthString;
        glyphLayout.setText(font, uiString);
        float width = glyphLayout.width;
        float height = glyphLayout.height;
        float x = (Constants.BOARD_WIDTH - width) / 2;
        float y = Constants.BOARD_HEIGHT - height;
        font.draw(batch, glyphLayout, x, y);
    }
}
