package app.view.gameview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import app.utils.Constants;
import app.utils.Pair;

/**
 * Class is responsible for rendering the in-game graphics for the background,
 * entities and UI.
 */
public class GameView {
    private ViewableGameWorld gameWorld;
    private TextureFactory textures = new TextureFactory();
    private float tileMapX;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthographicCamera entityCamera;
    private TextureRegion[] animationFrames = new TextureRegion[Constants.PLAYER_ANIMATION_FRAME];
    private Animation<TextureRegion> animation = new Animation<>(Constants.PLAYER_ANIMATION_SPEED, animationFrames);
    private float elapsedTime;

    /**
     * Creates a new gameview that renders the state of the given gameworld.
     * 
     * @param gameworld to be rendered
     */
    public GameView(ViewableGameWorld gameWorld) {
        this.gameWorld = gameWorld;
        initializeTextures();
        initializeTileMap();
        initializeEntityCamera();
    }

    private void initializeTextures() {
        for (Pair<String, Character> pair : Constants.TEXTURE_PATHS) {
            String path = pair.a();
            char key = pair.b();
            Texture texture = new Texture(path);
            textures.register(texture, key);
        }
    }

    private void initializeTileMap() {
        this.map = new TmxMapLoader().load(Constants.TILE_MAP_PATH);
        this.renderer = new OrthogonalTiledMapRenderer(map);
        this.camera = new OrthographicCamera(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        this.camera.position.set(Constants.BOARD_WIDTH / 2, Constants.BOARD_HEIGHT / 2, 0);
    }

    private void initializeEntityCamera() {
        this.entityCamera = new OrthographicCamera(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
        this.entityCamera.position.set(Constants.BOARD_WIDTH / 2, Constants.BOARD_HEIGHT / 2, 0);
        this.entityCamera.update();
    }

    private void renderPlayer(SpriteBatch batch, ViewableEntity entity) {
        ViewablePlayer player = (ViewablePlayer) entity;
        renderPlayerEffects(batch, player);
        renderPlayerSprites(batch, player);
    }

    private void renderPlayerSprites(SpriteBatch batch, ViewablePlayer player) {
        Rectangle hb = player.getDrawBox();
        int index = 0;

        char key = Constants.PLAYER_TEXTURE_KEY;
        key = player.isMovingUp() ? key : Character.toLowerCase(key);
        Texture playerSprite = textures.createObject(key);

        TextureRegion[][] tmpFrames = TextureRegion.split(playerSprite,
                playerSprite.getWidth() / 2,
                playerSprite.getHeight() / 2);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                this.animationFrames[index++] = tmpFrames[j][i];
            }
        }
        batch.draw((TextureRegion) this.animation.getKeyFrame(elapsedTime, true), hb.x, hb.y, hb.width, hb.height);
    }

    private void renderPlayerEffects(SpriteBatch batch, ViewablePlayer player) {
        Rectangle hitBox = player.getDrawBox();
        float x = hitBox.x;
        float size = hitBox.width;
        float height = hitBox.height;
        float diff = (size - height) / 2;
        float y = hitBox.y - diff;
        for (char effectKey : player.getEffects()) {
            Texture effectTexture = textures.createObject(effectKey);
            batch.draw(effectTexture, x, y, size, size);
        }
    }

    /**
     * Renders the game on screen.
     * 
     * @param spritebatch
     */
    public void render(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (elapsedTime >= this.animation.getAnimationDuration()) {
            elapsedTime = 0;
        }
        batch.begin();
        renderMap(batch);
        renderEntities(batch);
        renderUIView(batch);
        batch.end();
    }

    private void renderEntities(SpriteBatch batch) {
        batch.setProjectionMatrix(entityCamera.combined);
        for (ViewableEntity entity : gameWorld.getEntities()) {
            if (entity.getKey() == Constants.HIDDEN_KEY) {
                continue;
            }
            if (entity.getKey() == Constants.PLAYER_TEXTURE_KEY) {
                renderPlayer(batch, entity);
            } else {
                renderEntity(batch, entity);
            }
        }
    }

    private void renderEntity(SpriteBatch batch, ViewableEntity entity) {
        Rectangle hb = entity.getDrawBox();
        Texture texture = textures.createObject(entity.getKey());
        batch.draw(texture, hb.x, hb.y, hb.width, hb.height);
    }

    private void renderUIView(SpriteBatch batch) {
        UIView.renderUI(batch, gameWorld.getScore(), gameWorld.getPlayerHealth());
    }

    private void renderMap(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        if (gameWorld.shouldUpdateGame()) {
            tileMapX += Constants.TILE_MAP_SPEED * Gdx.graphics.getDeltaTime();
            if (tileMapX > Constants.BOARD_WIDTH * 2) {
                tileMapX = 0;
            }
        }
        camera.position.set(Constants.BOARD_WIDTH / 2 + tileMapX, Constants.BOARD_HEIGHT / 2, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();
    }

    /**
     * Dispose of resources when they are no longer needed.
     */
    public void dispose() {
        textures.dispose();
        map.dispose();
    }

}
