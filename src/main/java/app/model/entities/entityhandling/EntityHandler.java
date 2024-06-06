package app.model.entities.entityhandling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

import app.model.entities.Entity;
import app.model.entities.EntityType;
import app.model.entities.items.Item;
import app.model.entities.items.ItemEffect;
import app.model.entities.player.*;
import app.model.score.ScoreEvent;
import app.sound.Sound;
import app.sound.SoundEvent;
import app.utils.Constants;
import app.view.gameview.ViewableEntity;

/**
 * Class is responsible for handling the update of the entities and thus also
 * the main logical components of the gameplay.
 */
public class EntityHandler implements EntityGameWorld {

    private Player player = new Player();
    private int playerHealth = Constants.PLAYER_INIT_HEALTH;
    private float timeSinceFoe;
    private float timeSinceItem;
    private EntityFactory entityFactory = new EntityFactory();
    private List<Entity> entities = new ArrayList<>();
    private List<Character> foeKeys = new ArrayList<>();
    private List<Character> itemKeys = new ArrayList<>();
    private Random rnd = new Random();
    private EntityHandlerGameWorld gameWorld;
    private float punchCooldown;

    /**
     * Creates a new entity handler that reports to the given game world.
     * 
     * @param gameworld
     */
    public EntityHandler(EntityHandlerGameWorld gameWorld) {
        this.gameWorld = gameWorld;
        initializeEntities(Constants.LIST_OF_FOES, foeKeys);
        initializeEntities(Constants.LIST_OF_ITEMS, itemKeys);
    }

    /**
     * Constructor only for testing purposes. Not to be used in production.
     */
    public EntityHandler(EntityHandlerGameWorld gameWorld, int playerHealth) {
        this(gameWorld);
        this.playerHealth = playerHealth;
    }

    private void initializeEntities(Entity[] listOfEntities, List<Character> keyList) {
        for (Entity entity : listOfEntities) {
            entityFactory.register(entity, entity.getKey());
            keyList.add(entity.getKey());
        }
    }

    /**
     * Updates all the entities in the game and adds new entities.
     * 
     * @param delta
     * @param should add more entities
     */
    public void update(float delta, boolean shouldAddEntities) {
        if (shouldAddEntities) {
            addFoe(delta);
            addItem(delta);
        }
        updatePlayer(delta);
        updateEntityList(entities, delta);
    }

    private void updatePlayer(float delta) {
        punchCooldown -= delta;
        punchCooldown = Math.max(punchCooldown, 0);
        player.update(delta, null);
    }

    /**
     * No more entities left.
     * 
     * @return list of entities empty
     */
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    /**
     * Is the player standing on the ground.
     * 
     * @return player on ground
     */
    public boolean isPlayerStandingOnTheGround() {
        return player.isStandingOnTheGround();
    }

    /**
     * Ascends the player
     */
    public void ascend() {
        player.ascend();
    }

    /**
     * Makes the player fall.
     */
    public void fall() {
        player.fall();
    }

    private void updateEntityList(List<Entity> entities, float delta) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            updateEntity(entity, delta);

            if (!entity.isAlive()) {
                entities.remove(i--);
            }
        }
    }

    private void updateEntity(Entity entity, float delta) {
        if (!entity.isAlive())
            return;

        // Updates the position of the entity
        entity.update(delta, this);

        if (entity.getType() == EntityType.PLAYER_WEAPON) {
            weaponCollisionCheck(entity);
        }
        playerCollisionCheck(entity);

        boundsCheck(entity);
    }

    private void boundsCheck(Entity entity) {
        Rectangle hitBox = entity.getHitBox();
        boolean outsideLeft = hitBox.x < -hitBox.width;
        int margin = 100; // For not removing entities that just spawned
        boolean outsideRight = hitBox.x > Constants.BOARD_WIDTH + hitBox.width + margin;
        if (outsideLeft || outsideRight) {
            entity.kill();
        }
    }

    private void playerCollisionCheck(Entity entity) {
        if (!player.isAlive()) {
            return;
        }
        if (entity.isColliding(player)) {
            switch (entity.getType()) {
                case FOE:
                    playerHit();
                    entity.kill();
                    break;
                case ITEM:
                    Item item = (Item) entity;
                    item.onAdd(this);
                    player.addItem(item);
                    entity.kill();
                    break;
                case TIMED_FOE:
                    playerHit();
                    if (player.isAlive()) {
                        return;
                    }
                    break;
                case PLAYER_WEAPON:
                    return;
                default:
                    throw new IllegalArgumentException(
                            entity.getType() + " is not a supported type for the player to collide with.");
            }
            
            Sound.play(SoundEvent.PLAYER_HIT);
        }
    }

    private void playerHit() {
        if (player.hasEffect(ItemEffect.INVINCIBLE)) {
            return;
        }
        if (playerHealth > 0) {
            playerHealth--;
        }
        if (playerHealth <= 0) {
            player.kill();
            gameWorld.onGameOver();
        }
    }

    private void weaponCollisionCheck(Entity weapon) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            boolean notWeapon = entity.getType() != EntityType.PLAYER_WEAPON;
            boolean notPlayer = entity.getType() != EntityType.PLAYER;
            boolean notTimedFoe = entity.getType() != EntityType.TIMED_FOE;
            boolean correctTarget = notWeapon && notPlayer && notTimedFoe;
            boolean hasHitEntity = weapon.isColliding(entity);
            if (correctTarget && hasHitEntity) {
                entity.kill();
                weapon.kill();
                if (entity.getType() == EntityType.FOE) {
                    gameWorld.postScoreEvent(ScoreEvent.SHOT_FOE);
                }
            }
        }
    }

    private void addFoe(float delta) {
        timeSinceFoe = addEntity(delta, timeSinceFoe, Constants.SPAWNING_ACCELERATION_FOE,
                Constants.SPAWNING_FREQUENCY_FOE, getFoeKey());
    }

    private char getFoeKey() {
        int level = gameWorld.getLevel() > foeKeys.size() ? foeKeys.size() : gameWorld.getLevel() + 1;
        return foeKeys.get(rnd.nextInt(level));
    }

    private void addItem(float delta) {
        timeSinceItem = addEntity(delta, timeSinceItem, 0, Constants.SPAWNING_FREQUENCY_ITEM, getItemKey());
    }

    private char getItemKey() {
        return itemKeys.get(rnd.nextInt(itemKeys.size()));
    }

    /**
     * Adds an entity if the time since the last entity was added is greater than
     * the spawning frequency.
     * 
     * @param delta
     * @param time     since last entity was spawned
     * @param spawning acceleration of entity
     * @param spawning frequency of entity
     * @param key      for factory
     */
    private float addEntity(float delta, float timeSinceEntity, float spawnAcc, float spawnFreq, char key) {
        timeSinceEntity += delta;
        float acceleration = gameWorld.getScore() * spawnAcc + 1;
        float frequency = spawnFreq / acceleration;
        if (timeSinceEntity > frequency) {
            timeSinceEntity = 0;
            Entity entity = entityFactory.createObject(key);
            entities.add(entity);
        }
        return timeSinceEntity;
    }

    @Override
    public Rectangle getPlayerHitBox() {
        return player.getHitBox();
    }

    /**
     * Get the list of all entities currently in the game.
     * 
     * @return list of entities
     */
    public List<ViewableEntity> getEntities() {
        List<ViewableEntity> allEntities = new LinkedList<>(entities);
        allEntities.add(0, player);
        return allEntities;
    }

    /**
     * Resets the state of the entities.
     */
    public void reset() {
        player.reset();
        entities.clear();
        timeSinceFoe = 0;
        timeSinceItem = 0;
        playerHealth = Constants.PLAYER_INIT_HEALTH;
    }

    /**
     * Shoots if the player has the gun item.
     */
    public void shoot() {
        if (player.hasEffect(ItemEffect.GUN)) {
            Entity bullet = new Bullet(this);
            entities.add(bullet);
        }
    }

    /**
     * Makes the player punch and kill foes if the player has a punch item.
     */
    public void punch() {
        if (player.hasEffect(ItemEffect.PUNCH)) {
            if (punchCooldown <= 0) {
                punchCooldown = Constants.PUNCH_COOL_DOWN;
                Entity punch = new PunchEntity(this);
                entities.add(punch);
            }
        }
    }

    @Override
    public void spawnEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Gets the current health of the player
     * 
     * @return player health
     */
    public int getPlayerHealth() {
        return playerHealth;
    }

    @Override
    public void oneUp() {
        playerHealth++;
    }

}
