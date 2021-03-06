package com.chimkontran.madball.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.Scenes.Hub;
import com.chimkontran.madball.Sprites.Ball;

/**
 * Created by chimkontran on 12/12/2017.
 */

public class PlayScreen implements Screen {

    private Madball game;

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
        this.game = game;

        // Camera to follow player
        gameCamera = new OrthographicCamera();
        // Maintain game aspect ratio
        gamePort = new FitViewport(Madball.V_WIDTH / Madball.PPM, Madball.V_HEIGHT / Madball.PPM, gameCamera);
        // Display game info
        hub = new Hub(game.batch);

        // Load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("grassMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Madball.PPM);

        // Set camera at the start of the map
        gameCamera.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        ball = new Ball(world);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Create WALL Body / Fixture
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Madball.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Madball.PPM);
            body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth() / 2 / Madball.PPM, rectangle.getHeight() / 2 / Madball.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

    }

    @Override
    public void show() {

    }

    //
    public void handleInput(float dTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && ball.box2dBody.getLinearVelocity().y <= 2)
            ball.box2dBody.applyLinearImpulse(new Vector2(0, 0.5f), ball.box2dBody.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.S) && ball.box2dBody.getLinearVelocity().y >= -2)
            ball.box2dBody.applyLinearImpulse(new Vector2(0, -0.5f), ball.box2dBody.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.D) && ball.box2dBody.getLinearVelocity().x <= 2)
            ball.box2dBody.applyLinearImpulse(new Vector2(0.5f, 0), ball.box2dBody.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.A) && ball.box2dBody.getLinearVelocity().x >= -2)
            ball.box2dBody.applyLinearImpulse(new Vector2(-0.5f, 0), ball.box2dBody.getWorldCenter(), true);
    }

    public void update(float dTime) {
        // Handle user input
        handleInput(dTime);

        world.step(1/60f, 6, 2);

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
    public void dispose() {
    }
}
