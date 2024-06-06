package app.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class FactoryTest {
    
    private Factory<Integer> factory;
    private char key = 'i';

    @BeforeEach
    void setup() {
        factory = new Factory<>();
    }

    @Test
    void testDuplicateKeyThrowsException() {
        factory.register(5, 'i');
        assertThrows(IllegalArgumentException.class, () -> factory.register(5, 'i'));
    }

    @Test
    void testUnregisteredKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> factory.createObject('i'));
    }

    @Test
    void testCreateObject() {
        int i = 1;
        factory.register(i, key);
        assertEquals(i, factory.createObject(key));
    }
}
