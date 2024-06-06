package app.view.menuview;

import org.junit.jupiter.api.*;

public class MenuViewTest {

    @BeforeEach
    void setUp() {
        // Kjør programmet
    }

    @Test
    void buttonReactTest() {
        // Flytt musen over en av knappene
        // Hold inn venstre muse-knapp
        // Se at knappen endrer farge
    }

    @Test
    void startTest() {
        // Flytt musen over start-knappen
        // Trykk på knappen
        // Se at menyen forsvinner og spillet starter
    }

    @Test
    void helpTest() {
        // Flytt musen over help-knappen
        // Trykk på knappen
        // Se at det popper opp en boks med instruksjoner om hvordan man spiller
        // Se at det er en OK-knapp
        // Flytt musen til den
        // Trykk på OK-knappen
        // Se at boksen forsvinner
        // Se at man er tilbake til hovedmenyen
    }

    @Test
    void highscoresTest() {
        // Flytt musen over highscorse-knappen
        // Trykk på knappen
        // Se at det popper opp en boks med tall som indikerer scorer
        // Se at det er en OK-knapp
        // Flytt musen til den
        // Trykk på OK-knappen
        // Se at boksen forsvinner
        // Se at man er tilbake til hovedmenyen
    }

    @Test
    void quitTest() {
        // Flytt musen over quit-knappen
        // Trykk på knappen
        // Se at spillet lukker seg
    }

}
