package com.chimkontran.madball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.scenes.Hub;
import com.chimkontran.madball.sprites.Ball;
import com.chimkontran.madball.system.InputTracker;
import com.chimkontran.madball.tools.Box2DWorldCreator;

/**
 * Created by chimkontran on 12/12/2017.
 */

public class PlayScreen implements Screen {

    private Madball game;
    TextureAtlas textureAtlas;

    private OrthographicCamera gameCamera;
    private Viewport gamePort;
    private Hub hub;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d variables
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Ball ball;

    public PlayScreen(Madball game){
        // Add Texture
        textureAtlas = new TextureAtlas("ballsTexture.pack");

        this.game = game;
        // Camera to follow player
        gameCamera = new OrthographicCamera();
        // Maintain game aspect ratio
        gamePort = new FitViewport(Madball.V_WIDTH / Madball.PPM, Madball.V_HEIGHT / Madball.PPM, gameCamera);
        // Display game info
        hub = new Hub(game.batch);

        // Load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("arenaMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Madball.PPM);

        // Set camera at the start of the map
        gameCamera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        // Set 0 gravity
        world = new World(new Vector2(0, 0), true);

        // Draw debugline
        debugRenderer = new Box2DDebugRenderer();

        // Create Ball (player)
        ball = new Ball(world, this);

        // Create World
        new Box2DWorldCreator(world, map);

    }

    public TextureAtlas getTextureAtlas()
    {
        return  textureAtlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dTime) {
        // Handle user input
        float speed = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && ball.box2dBody.getLinearVelocity().y <= 2)
            ball.box2dBody.setLinearVelocity(new Vector2(ball.box2dBody.getLinearVelocity().x, speed));
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && ball.box2dBody.getLinearVelocity().y >= -2)
            ball.box2dBody.setLinearVelocity(new Vector2(ball.box2dBody.getLinearVelocity().x, -speed));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && ball.box2dBody.getLinearVelocity().x <= 2)
            ball.box2dBody.setLinearVelocity(new Vector2(speed, ball.box2dBody.getLinearVelocity().y));
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && ball.box2dBody.getLinearVelocity().x >= -2)
            ball.box2dBody.setLinearVelocity(new Vector2(-speed, ball.box2dBody.getLinearVelocity().y));

        // Handle when user NOT PRESSING key
        if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN))
            ball.box2dBody.setLinearVelocity(new Vector2(ball.box2dBody.getLinearVelocity().x, 0));
        if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            ball.box2dBody.setLinearVelocity(new Vector2(0, ball.box2dBody.getLinearVelocity().y));
    }



    public void update(float dTime) {
        // Handle user input
        handleInput(dTime);

        world.step(1/60f, 6, 2);

        ball.update(dTime);

        // Make camera follow Ball (player)
        gameCamera.position.x = ball.box2dBody.getPosition().x;
        gameCamera.position.y = ball.box2dBody.getPosition().y;


        // Update correct coordinate after changes
        gameCamera.update();
        // Only render what User can see
        renderer.setView(gameCamera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // White game screen
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render game Map
        renderer.render();

        // Render Box2d Debug lines
        debugRenderer.render(world, gameCamera.combined);

        game.batch.setProjectionMatrix(gameCamera.combined);
        game.batch.begin();
        ball.draw(game.batch);
        game.batch.end();

        // Show Camera
        game.batch.setProjectionMatrix(hub.stage.getCamera().combined);
        hub.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        map.dispose();
        world.dispose();
        renderer.dispose();
        debugRenderer.dispose();
        hub.dispose();
    }
}
