package app.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A general purpose object factory.
 */
public class Factory<T> {

    protected Map<Character, T> registered = new HashMap<>();

    /**
     * Register the given value to the factory with the given char as key.
     * 
     * @throws IllegalArgumentException if key is already in use
     * @param value
     * @param key
     */
    public void register(T value, char key) {
        if (registered.containsKey(key)) {
            throw new IllegalArgumentException("The key: '" + key + "' is already in use.");
        }
        registered.put(key, value);
    }

    /**
     * Gives an object with the given key.
     * 
     * @throws IllegalArgumentException if the key is not registered
     * @param key
     * @return object
     */
    public T createObject(char key) {
        T value = registered.get(key);
        if (value == null) {
            throw new IllegalArgumentException("The key: '" + key + "' is not assigned to any entity");
        }
        return value;
    }
}
