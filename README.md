# INF112 Project - *Angel Run*

* Team: *LoveGame* (Team 6 på gruppe 8): *Amiley, Mathias, Vebjørn, Julie, Thone*

## Om spillet
*«Fly så langt man klarer. Unngå hindre som flammekuler, flammevegger og laserstråler. Plukk opp ulike gjenstander for å få kule krefter som varer i 5 sekunder. Hvis du plukker opp en banan får du ett ekstra liv og du vil lyse grønt. Plukker du opp et eple så kan du slå hindre ved å bruke tasten 'd' og du vil lyse rosa. Plukker du opp en stjerne vil du være udødelig og lyse blått. Hvis du plukker opp et hjerte så kan du skyte lyskuler med tasten 'f' og du vil lyse gult. Highscoren din vil bli lagret lokalt og denne kan man see i menyen til spillet.»*

### Struktur
```plaintext
├── README.md
├── doc
├── pom.xml
├── referat
├── scores.txt
└── src
    ├── main
    │   ├── java
    │   │   └── app
    │   │       ├── Main.java
    │   │       ├── controller
    │   │       │   └── EndlessRunnerGame.java
    │   │       ├── model
    │   │       │   ├── GameState.java
    │   │       │   ├── GameWorld.java
    │   │       │   ├── entities
    │   │       │   │   ├── Entity.java
    │   │       │   │   ├── EntityType.java
    │   │       │   │   ├── TimedEntity.java
    │   │       │   │   ├── enemies
    │   │       │   │   ├── entityhandling
    │   │       │   │   ├── items
    │   │       │   │   └── player
    │   │       │   └── score
    │   │       │       ├── Score.java
    │   │       │       ├── ScoreEvent.java
    │   │       │       └── Scoreboard.java
    │   │       ├── sound
    │   │       │   ├── Sound.java
    │   │       │   └── SoundEvent.java
    │   │       ├── utils
    │   │       │   ├── Constants.java
    │   │       │   ├── Factory.java
    │   │       │   └── Pair.java
    │   │       └── view
    │   │           ├── gameview
    │   │           │   ├── GameView.java
    │   │           │   ├── TextureFactory.java
    │   │           │   ├── UIView.java
    │   │           │   ├── ViewableEntity.java
    │   │           │   ├── ViewableGameWorld.java
    │   │           │   └── ViewablePlayer.java
    │   │           └── menuview
    │   │               ├── GameToggleBox.java
    │   │               ├── GameWorldListener.java
    │   │               ├── LibGDXToggleBox.java
    │   │               └── MenuView.java
    │   └── resources
    │       ├── bakgrunnsbilder
    │       ├── bakgrunnskode
    │       ├── entities
    │       ├── loveGameMap.tmx
    │       ├── lyd
    │       └── skin
    └── test
        └── java
            └── app
                ├── controller
                │   └── EndlessRunnerGameTest.java
                ├── model
                │   ├── GameWorldTest.java
                │   ├── entities
                │   │   ├── EntityTest.java
                │   │   ├── TimedEntityTest.java
                │   │   ├── enemies
                │   │   ├── entityhandling
                │   │   ├── items
                │   │   └── player
                │   └── scoreboard
                │       ├── ScoreTest.java
                │       └── ScoreboardTest.java
                ├──  utils
                │   └── FactoryTest.java
                └── view
                    ├── gameview
                    │   ├── GameViewTest.java
                    │   └── UIViewTest.java
                    └── menuview
                        └── MenuViewTest.java
```

## Kjøring
* Kompileres med `mvn package`.
* Testes med `mvn test`.
* Kjøres med `mvn exec:java`.
* Krever Java 21 eller senere

## Testing
* View og controller blir testet av manuelle tester
* Model blir testet av unit tester

## Kjente feil
Ingenting enda.

## Credits
Bakgrunn:
https://opengameart.org/content/background-clouds-and-mountains-parallax

Obstacle:
https://opengameart.org/content/homemade-flame

Invincible item:
https://opengameart.org/content/star

Shooting item:
https://opengameart.org/content/blue-life-heart

Laser warning, laser beam, one-up item, punch item, punch:
Tegnet av Vebjørn på gruppen.

Karakter, aura rundt karakter, bullet:
Tegnet av Thone på gruppen.

Lyd:
https://mixkit.co/free-sound-effects/hit/ "Arrow shot through air"

Skin til menyen:
https://ray3k.wordpress.com/clean-crispy-ui-skin-for-libgdx/
