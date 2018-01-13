package com.chimkontran.madball.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.screens.PlayScreen;
import com.chimkontran.madball.system.Controller;

/**
 * Created by chimkontran on 12/14/2017.
 */

public class Ball extends Sprite {
    public static final int BALL_WIDTH = 30;
    public static final int BALL_HEIGHT = 30;

    public enum State {LOOKING_UP, LOOKING_DOWN, LOOKING_LEFT, LOOKING_RIGHT}

    public State currentState;
    public State previousState;
    private float stateTimer;

    private boolean isLookingUp;
    private boolean isLookingDown;
    private boolean isLookingLeft;
    private boolean isLookingRight;

    public TextureRegion ballLookUP;
    public TextureRegion ballLookDown;
    public TextureRegion ballLookLeft;
    public TextureRegion ballLookRight;

    public World world;
    public Body box2dBody;
    Controller controller;

    public Ball(World world, PlayScreen screen) {
        super(screen.getTextureAtlas().findRegion("swat"));
        this.world = world;

        // Set state
        currentState = State.LOOKING_RIGHT;
        previousState = State.LOOKING_RIGHT;
        stateTimer = 0;
        isLookingRight = true;
        isLookingDown = false;
        isLookingLeft = false;
        isLookingUp = false;
        controller = new Controller();

        // Get texture for Ball (player)
        ballLookDown = new TextureRegion(getTexture(), 2, 75, 100, 100);
        ballLookLeft = new TextureRegion(getTexture(), 104, 75, 100, 100);
        ballLookRight = new TextureRegion(getTexture(), 206, 75, 100, 100);
        ballLookUP = new TextureRegion(getTexture(), 308, 75, 100, 100);

        defineBall();
        setBounds(0, 0, BALL_WIDTH / Madball.PPM, BALL_HEIGHT / Madball.PPM);
        setRegion(ballLookRight);
    }

    // Set LOOKING STATE
    public void setLookingUp(boolean state) {
        this.isLookingUp = state;
        this.isLookingDown = false;
        this.isLookingRight = false;
        this.isLookingLeft = false;
    }
    public void setLookingDown(boolean state) {
        this.isLookingDown = state;
        this.isLookingUp = false;
        this.isLookingRight = false;
        this.isLookingLeft = false;
    }
    public void setLookingLeft(boolean state) {
        this.isLookingLeft = state;
        this.isLookingUp = false;
        this.isLookingDown = false;
        this.isLookingRight = false;
    }
    public void setLookingRight(boolean state) {
        this.isLookingRight = state;
        this.isLookingLeft = false;
        this.isLookingUp = false;
        this.isLookingDown = false;
    }

    // Update Ball location
    public void update(float dTime) {
        setPosition(box2dBody.getPosition().x - getWidth() / 2, box2dBody.getPosition().y - getHeight() / 2);
        // SET REGION
        TextureRegion textureRegion = getBallTexture();
        if (textureRegion != null) {
            setRegion(getBallTexture());
        } else {
            System.out.println(textureRegion);
        }
    }

    // Get TextureRegion based on STATE
    public TextureRegion getBallTexture() {
        TextureRegion textureRegion;
        currentState = getState();

        // Get Texture base on STATE
        switch (currentState) {
            case LOOKING_UP:
                textureRegion = ballLookUP;
                break;
            case LOOKING_DOWN:
                textureRegion = ballLookDown;
                break;
            case LOOKING_LEFT:
                textureRegion = ballLookLeft;
                break;
            case LOOKING_RIGHT:
                textureRegion = ballLookRight;
                break;
            default:
                textureRegion = ballLookRight;
                break;
        }
        previousState = currentState;
        return textureRegion;
    }

    // Get STATE
    private State getState() {
        if (isLookingRight)
        return State.LOOKING_RIGHT;
        else if (isLookingLeft)
            return State.LOOKING_LEFT;
        else if (isLookingUp)
            return State.LOOKING_UP;
        else if (isLookingDown)
            return State.LOOKING_DOWN;
        else return currentState;
    }

    // Define BALL (player)
    private void defineBall() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(64 / Madball.PPM, 64 / Madball.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / Madball.PPM);

        // Set bit category
        fixtureDef.filter.categoryBits = Madball.BALL_BIT;
        // What can Ball collide with
        fixtureDef.filter.maskBits = Madball.DEFAULT_BIT | Madball.GOLD_BIT;

        fixtureDef.shape = shape;
        box2dBody.createFixture(fixtureDef).setUserData("ball_body");

    }
}
