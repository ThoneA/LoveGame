package app.model.entities.enemies.laser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.enemies.Foe;
import app.model.entities.entityhandling.EntityGameWorld;
import app.utils.Constants;

/**
 * Adds multiple laser warnings to the game.
 */
public class LaserSpawner extends Foe {

    /**
     * Creates a new laser spawner.
     */
    public LaserSpawner() {
        drawBox = new Rectangle();
    }

    @Override
    public char getKey() {
        return Constants.HIDDEN_KEY;
    }

    @Override
    public void update(float delta, EntityGameWorld gameWorld) {
        int numberOfSlots = Constants.NUMBER_OF_LASER_SLOTS;
        int numberOfLasers = (int) Math.ceil(numberOfSlots / 2.);
        List<Integer> usedSlots = getSlots(numberOfLasers, numberOfSlots);
        for (int slot : usedSlots) {
            gameWorld.spawnEntity(new LaserWarning(slot));
        }
        kill();
    }

    private List<Integer> getSlots(int numberOfLasers, int numberOfSlots) {
        List<Integer> slots = new ArrayList<>();
        for (int i = 0; i < numberOfSlots; i++) {
            slots.add(i);
        }
        Random rnd = new Random();
        while (slots.size() > numberOfLasers) {
            slots.remove(rnd.nextInt(slots.size()));
        }
        return slots;
    }
    
}
