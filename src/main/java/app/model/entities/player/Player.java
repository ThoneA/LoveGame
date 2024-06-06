package app.model.entities.player;

import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.model.entities.entityhandling.EntityGameWorld;
import app.model.entities.items.Item;
import app.model.entities.items.ItemEffect;
import app.utils.Constants;
import app.view.gameview.ViewablePlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;

/**
 * Represents the player character in the Endless Runner game.
 * Handles player movement and input.
 */
public class Player extends Entity implements ViewablePlayer {

    private boolean isMovingUp;
    private int heighestPosition = Constants.BOARD_HEIGHT - Constants.PLAYER_HEIGHT;
    private float velocity;
    private List<Item> items = new ArrayList<>();

    /**
     * Initializes the player character.
     */
    public Player() {
        float x = Constants.PLAYER_STARTING_POSITION.x;
        float y = Constants.PLAYER_STARTING_POSITION.y;
        int height = Constants.PLAYER_HEIGHT;
        int width = Constants.PLAYER_WIDTH;
        this.drawBox = new Rectangle(x, y, width, height);
    }

    /**
     * Updates the player's position and state.
     * 
     * @param delta time since the last update
     */
    public void update(float delta, EntityGameWorld gameWorld) {
        updateVelocity(delta);
        updateGravity(delta);
        updatePosition(delta);
        updateItems(delta);
    }

    private void updateItems(float delta) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.updateTimer(delta);
            if (item.effectOver()) {
                items.remove(i--);
            }
        }
    }

    private void updateVelocity(float delta) {
        if (isOnTheCeiling()) {
            velocity = 0;
        }
        if (isMovingUp) {
            velocity += Constants.PLAYER_ACCELERATION * delta;
        }
    }

    private void updateGravity(float delta) {
        if (!isStandingOnTheGround()) {
            velocity -= Constants.GRAVITY * delta;
        }
    }

    /**
     * Do the player stand on the ground.
     * 
     * @return true if player standing on the ground
     */
    public boolean isStandingOnTheGround() {
        return drawBox.y == 0;
    }

    private boolean isOnTheCeiling() {
        return drawBox.y == heighestPosition;
    }

    /**
     * Updates the position based on velocity, delta time and the boundries of the
     * world.
     * 
     * @param delta
     */
    private void updatePosition(float delta) {
        drawBox.y += velocity * delta;

        drawBox.y = Math.max(0, drawBox.y);
        drawBox.y = Math.min(heighestPosition, drawBox.y);
    }

    /**
     * Changes the direction of the player to move upwards.
     */
    public void ascend() {
        isMovingUp = true;
        if (isStandingOnTheGround()) {
            velocity = Constants.PLAYER_JUMP_SPEED;
        } else {
            velocity = 0;
        }
    }

    /**
     * Changes the direction of the player to move downwards.
     */
    public void fall() {
        isMovingUp = false;
    }

    /**
     * Resets the player to its initial state.
     */
    public void reset() {
        drawBox.x = Constants.PLAYER_STARTING_POSITION.x;
        drawBox.y = Constants.PLAYER_STARTING_POSITION.y;
        velocity = 0;
        fall();
        items.clear();
        alive = true;
    }

    @Override
    public boolean isMovingUp() {
        return isMovingUp;
    }

    @Override
    public char getKey() {
        return Constants.PLAYER_TEXTURE_KEY;
    }

    /**
     * Adds item to the player.
     * 
     * @param item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }

    /**
     * Checks if the player has the given item effect
     * 
     * @param effect
     * @return has effect
     */
    public boolean hasEffect(ItemEffect effect) {
        for (Item item : items) {
            if (item.getEffect() == effect) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Rectangle getHitBox() {
        float x = drawBox.x + drawBox.width / 3;
        float y = drawBox.y;
        float width = drawBox.width / 3;
        float height = drawBox.height;
        Rectangle hitBox = new Rectangle(x, y, width, height);
        return hitBox;
    }

    @Override
    public Set<Character> getEffects() {
        Set<Character> effects = new HashSet<>();
        for (Item item : items) {
            effects.add(item.getEffectKey());
        }
        return effects;
    }
}
