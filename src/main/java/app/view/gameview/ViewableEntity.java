package app.view.gameview;

import com.badlogic.gdx.math.Rectangle;

/**
 * Interface intended to be used for in the GameView class to restrict what it
 * can access to entities.
 */
public interface ViewableEntity {

    /**
     * Get the draw box of the entity. With x and y coordinates, and width and
     * height. Returns a copy of the draw box as a new Rectangle object.
     * 
     * @return draw box of the entity
     */
    public Rectangle getDrawBox();

    /**
     * Get the key that should be associated with the entity.
     * 
     * @return key as char
     */
    public abstract char getKey();
}
