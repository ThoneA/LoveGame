package app.model.entities.entityhandling;

import app.model.entities.Entity;
import app.utils.Factory;

/**
 * An object factory for the Entity class. You can register and create
 * entities using the factory.
 */
public class EntityFactory extends Factory<Entity> {

    /**
     * Creates a new entity with the given key.
     * 
     * @throws IllegalArgumentException if the key is not registered
     * @throws RuntimeException         if it is unable to create an instance of the
     *                                  class
     * @param key
     * @return new entity
     */
    @Override
    public Entity createObject(char key) {
        Entity entity = registered.get(key);
        if (entity == null) {
            throw new IllegalArgumentException("The key: '" + key + "' is not assigned to any entity");
        }

        Class<? extends Entity> entityClass = entity.getClass();

        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create an instance of the entity", e);
        }
    }
}
