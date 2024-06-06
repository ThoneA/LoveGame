package app.view.gameview;

import com.badlogic.gdx.graphics.Texture;

import app.utils.Factory;

/**
 * Object factory for textures.
 */
public class TextureFactory extends Factory<Texture> {

    /**
     * Disposes of all the registered textures and clears the factory.
     */
    public void dispose() {
        registered.forEach((key, value) -> {
            value.dispose();
        });
        registered.clear();
    }
    
}
