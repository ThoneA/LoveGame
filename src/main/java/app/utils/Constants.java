package app.utils;

import com.badlogic.gdx.math.Vector2;

import app.model.entities.Entity;
import app.model.entities.enemies.*;
import app.model.entities.enemies.laser.LaserSpawner;
import app.model.entities.items.*;
import app.sound.SoundEvent;

import java.util.List;
import java.util.Arrays;

/**
 * Contains Constants that can be easily accessed by all classes.
 */
public class Constants {

    // General board
    public static final int BOARD_HEIGHT = 608;
    public static final int BOARD_WIDTH = 1088;

    // General physics
    public static final int GRAVITY = (int) (BOARD_HEIGHT * 2.5);

    // Player
    public static final int PLAYER_HEIGHT = BOARD_HEIGHT / 5;
    public static final int PLAYER_WIDTH = PLAYER_HEIGHT / 40 * 59;
    public static final Vector2 PLAYER_STARTING_POSITION = new Vector2(PLAYER_HEIGHT, 0);
    public static final int PLAYER_JUMP_SPEED = PLAYER_HEIGHT * 3;
    public static final int PLAYER_ACCELERATION = BOARD_HEIGHT * 4;
    public static final float PLAYER_ANIMATION_SPEED = 1f / 10f;
    public static final int PLAYER_ANIMATION_FRAME = 4;
    public static final int PLAYER_INIT_HEALTH = 1;

    // Entities
    public static final Entity[] LIST_OF_FOES = {
            new Obstacle(),
            new Wall(),
            new FollowingEnemy(),
            new BouncingEnemy(),
            new LaserSpawner(),
    };
    public static final Entity[] LIST_OF_ITEMS = {
            new ShootingItem(),
            new InvincibleItem(),
            new OneUpItem(),
            new PunchItem(),
    };

    public static final float SPAWNING_FREQUENCY_FOE = 2.5f;
    public static final float SPAWNING_FREQUENCY_ITEM = 15f;
    public static final float SPAWNING_ACCELERATION_FOE = 0.005f;
    public static final float SPAWNING_ACCELERATION_ITEM = 0.003f;

    // Obstacle
    public static final int OBSTACLE_SIZE = PLAYER_HEIGHT / 2;
    public static final int OBSTACLE_SPEED = BOARD_HEIGHT;

    // Wall
    public static final int WALL_WIDTH = BOARD_HEIGHT / 20;
    public static final int WALL_HEIGHT = PLAYER_HEIGHT * 2;
    public static final int WALL_SPEED = (int) ((BOARD_HEIGHT / 5) * 3.5);

    // Enemy
    public static final int ENEMY_SPEED = OBSTACLE_SPEED;
    public static final float ENEMY_FOLLOW_STOP = PLAYER_WIDTH * 1.05f;

    // Item
    public static final int ITEM_SIZE = PLAYER_HEIGHT / 3;
    public static final int ITEM_SPEED = BOARD_HEIGHT;
    public static final int ITEM_DURATION = 5; // Seconds

    // Bullet
    public static final int BULLET_SIZE = PLAYER_HEIGHT / 6;
    public static final int BULLET_SPEED = BOARD_WIDTH / 2;

    // Punch
    public static final float PUNCH_DURATION = 0.5f; // Seconds
    public static final float PUNCH_COOL_DOWN = .2f + PUNCH_DURATION; // Seconds

    // Laser
    public static final float LASER_BEAM_DURATION = .75f;
    public static final float LASER_WARNING_DURATION = 2f;
    public static final int NUMBER_OF_LASER_SLOTS = 5;
    public static final float LASER_BEAM_HEIGHT = PLAYER_HEIGHT / 5;

    // Factory keys
    public static final char PLAYER_TEXTURE_KEY = 'P';
    public static final char OBSTACLE_KEY = 'O';
    public static final char WALL_KEY = 'W';
    public static final char FOLLOWING_ENEMY_KEY = 'F';
    public static final char SHOOTING_ITEM_KEY = 'S';
    public static final char INVINCIBLE_ITEM_KEY = 'I';
    public static final char BULLET_KEY = 'B';
    public static final char HIT_BOX_KEY = 'H';
    public static final char ONE_UP_ITEM_KEY = 'U';
    public static final char PUNCH_ITEM_KEY = 'c';
    public static final char PUNCH_ENTITY_KEY = 'C';
    public static final char BOUNCING_ENEMY_KEY = 'b';
    public static final char LASER_BEAM_KEY = 'l';
    public static final char LASER_WARNING_KEY = 'L';
    public static final char HIDDEN_KEY = 'H'; // For invisible entities

    // Scoreboard
    public static final String SCORE_FILE = "scores.txt";
    public static final int SCOREBOARD_SIZE = 5;
    public static final int[] SCORE_TO_LEVEL_TABLE = { 20, 40, 60, 80 };

    // Score
    public static final float SCORE_HITTING_FOE = 3;

    // Texture paths
    private static final String ENTITIES_PATH = "entities/";
    private static final String PICTURE_FORMAT = ".png";
    private static final String PLAYER_PATH = ENTITIES_PATH + "player/";
    private static final String ITEMS_PATH = ENTITIES_PATH + "items/";
    private static final String FOES_PATH = ENTITIES_PATH + "foes/";
    private static final String OBSTACLE_TEXTURE_PATH = FOES_PATH + "flame" + PICTURE_FORMAT;
    private static final String SHOOTING_ITEM_TEXTURE_PATH = ITEMS_PATH + "shooting_item" + PICTURE_FORMAT;
    private static final String INVINCIBLE_ITEM_TEXTURE_PATH = ITEMS_PATH + "invincible_item" + PICTURE_FORMAT;
    private static final String ONE_UP_ITEM_TEXTURE_PATH = ITEMS_PATH + "one_up_item" + PICTURE_FORMAT;
    private static final String BULLET_TEXTURE_PATH = ITEMS_PATH + "bullet" + PICTURE_FORMAT;
    private static final String PUNCH_TEXTURE_PATH = ITEMS_PATH + "punch" + PICTURE_FORMAT;
    private static final String PUNCH_ITEM_TEXTURE_PATH = ITEMS_PATH + "punch_item" + PICTURE_FORMAT;
    private static final String LASER_BEAM_TEXTURE_PATH = FOES_PATH + "laser_beam" + PICTURE_FORMAT;
    private static final String LASER_WARNING_TEXTURE_PATH = FOES_PATH + "laser_warning" + PICTURE_FORMAT;
    
    // Player Sprites
    private static final String PLAYER_SPRITE_DOWN_PATH = PLAYER_PATH + "player_down" + PICTURE_FORMAT;
    private static final String PLAYER_SPRITE_UP_PATH = PLAYER_PATH + "player_up" + PICTURE_FORMAT;
    
    // Player Items
    private static final String PLAYER_TEXTURE_INVINCIBLE_PATH = PLAYER_PATH + "invincible_effect" + PICTURE_FORMAT;
    private static final String PLAYER_TEXTURE_GUN_PATH = PLAYER_PATH + "gun_effect" + PICTURE_FORMAT;
    private static final String PLAYER_TEXTURE_PUNCH_PATH = PLAYER_PATH + "punch_effect" + PICTURE_FORMAT;
    private static final String PLAYER_TEXTURE_ONE_UP_PATH = PLAYER_PATH + "one_up_effect" + PICTURE_FORMAT;
    
    // Player Keys
    public static final char INVINCIBLE_EFFECT_KEY = 'i';
    public static final char GUN_EFFECT_KEY = 'g';
    public static final char PUNCH_EFFECT_KEY = 'M';
    public static final char ONE_UP_EFFECT_KEY = 'u';
    
    public static final List<Pair<String, Character>> TEXTURE_PATHS = Arrays.asList(
        new Pair<>(OBSTACLE_TEXTURE_PATH, OBSTACLE_KEY),
        new Pair<>(OBSTACLE_TEXTURE_PATH, WALL_KEY),
        new Pair<>(OBSTACLE_TEXTURE_PATH, FOLLOWING_ENEMY_KEY),
        new Pair<>(OBSTACLE_TEXTURE_PATH, BOUNCING_ENEMY_KEY),
        new Pair<>(SHOOTING_ITEM_TEXTURE_PATH, SHOOTING_ITEM_KEY),
        new Pair<>(INVINCIBLE_ITEM_TEXTURE_PATH, INVINCIBLE_ITEM_KEY),
        new Pair<>(ONE_UP_ITEM_TEXTURE_PATH, ONE_UP_ITEM_KEY),
        new Pair<>(PUNCH_ITEM_TEXTURE_PATH, PUNCH_ITEM_KEY),
        new Pair<>(BULLET_TEXTURE_PATH, BULLET_KEY),
        new Pair<>(PUNCH_TEXTURE_PATH, PUNCH_ENTITY_KEY),
        new Pair<>(LASER_BEAM_TEXTURE_PATH, LASER_BEAM_KEY),
        new Pair<>(LASER_WARNING_TEXTURE_PATH, LASER_WARNING_KEY),
        new Pair<>(PLAYER_SPRITE_UP_PATH, PLAYER_TEXTURE_KEY),
        new Pair<>(PLAYER_SPRITE_DOWN_PATH, Character.toLowerCase(PLAYER_TEXTURE_KEY)),
        new Pair<>(PLAYER_TEXTURE_INVINCIBLE_PATH, INVINCIBLE_EFFECT_KEY),
        new Pair<>(PLAYER_TEXTURE_GUN_PATH, GUN_EFFECT_KEY),
        new Pair<>(PLAYER_TEXTURE_PUNCH_PATH, PUNCH_EFFECT_KEY),
        new Pair<>(PLAYER_TEXTURE_ONE_UP_PATH, ONE_UP_EFFECT_KEY)
        );
    

    // View
    public static final int TILE_MAP_SPEED = 200;
    public static final String TILE_MAP_PATH = "src/main/resources/loveGameMap.tmx";

    // Sound
    public static final List<Pair<String, SoundEvent>> SOUND_FILE_PATHS = Arrays.asList(
        new Pair<>("./src/main/resources/lyd/hit_sound.wav",  SoundEvent.PLAYER_HIT)
        );
}
