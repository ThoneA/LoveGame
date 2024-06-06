package app.controller;

import org.junit.jupiter.api.*;

public class EndlessRunnerGameTest {

    @BeforeEach
    void setUp() {
        // Kjør programmet
        // Trykk på start-knappen
    }

    @Test
    void playerMovementTest() {
        // Se at spilleren beveger seg opp når man trykker på space
        // Se at spilleren beveger seg nedover når man slipper space
    }

    @Test
    void playerShootTest() {
        // Overlev med space-knappen frem til man treffer et hjerte-item
        // Trykk på f-knappen
        // Se at det kommer skudd fra spilleren
    }

    @Test
    void playerPunchTest() {
        // Overlev med space-knappen frem til man treffer et eple-item
        // Trykk på d-knappen
        // Se at det kommer grafikk til punch
    }

    @Test
    void pauseMenuTest() {
        // Trykk på escape-knappen
        // Se at det popper opp en boks med to knapper
        // Knappene skal si "OK" og "Back to menu"
        // Se at spillet pauser seg
    }

    @Test
    void pauseMenuResumeGameTest() {
        // Trykk på escape-knappen
        // Se at det popper opp en meny og spillet pauser seg
        // Trykk på OK-knappen
        // Se at spillet fortsetter
    }

    @Test
    void pauseMenuToMainMenuTest() {
        // Trykk på escape-knappen
        // Se at det popper opp en meny og spillet pauser seg
        // Trykk på "back to menu"-knappen
        // Se at menyen kommer opp
    }

}
