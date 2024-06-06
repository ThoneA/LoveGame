package app.view.gameview;

import org.junit.jupiter.api.*;

public class GameViewTest {

    @BeforeEach
    void setUp() {
        // Kjør programmet
        // Trykk på start-knappen
    }

    @Test
    void backgroundTest() {
        // Se at bakgrunnen beveger seg i en jevn bevegelse fra høyre til venstre
    }

    @Test
    void backgroundStopTest() {
        // Vent til spilleren blir truffet, alternativt bruk space-knappen for å treffe
        // en av fiendene med vilje
        // Se at bakgrunnen stopper når spilleren er på bakken og alle andre objekter er
        // ute av bildet
    }

    @Test
    void renderEntitiesTest() {
        // Se at det med jevne mellomrom kommer foes og items fra høyre siden av bilde
        // Bruk hold inne og slipp space-knappen for å styre spilleren mot høyden til foe/item
        // Pass på å treffe spilleren med de
        // Se at når grafikken til spilleren og foe/item treffer hverandre at foe/item forsvinner
    }

    @Test
    void renderPlayerBoundsTest() {
        // Se at spilleren starter på bakken som forventet
        // Hold inne space-knappen frem til spilleren treffer taket
        // Se at spilleren ikke beveger seg utenfor skjermen
    }

    @Test
    void renderPlayerSpritesTest() {
        // Se at spilleren ikke flakser med vingene
        // Hold inne space-knappen
        // Se at spilleren flakser med vingene
        // Slipp space-knappen
        // Se at spilleren går tilbake til samme animasjon som i starten
    }

    @Test
    void renderPlayerEffects() {
        // Styr spilleren med space-knappen
        // Unngå foes og treff items
        // Se at spilleren får en aura når den treffer item
        // Repeter frem til alle items er truffet
        // Begynn på nytt om nødvendig
    }
}
