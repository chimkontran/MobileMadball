package com.chimkontran.madball.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.screens.PlayScreen;

/**
 * Created by chimkontran on 1/13/2018.
 */

public class PinkBall extends Enemy {

    private float stateTime;
    private Animation walkAnimation;
    private Texture texture;
    private TextureRegion pinkBallTexture;
    private Array<TextureRegion> frames;

    public PinkBall(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        // Set Texture for PINK ball
        texture = new Texture(Gdx.files.internal("ballsTexture.png"));
        pinkBallTexture = new TextureRegion(texture, 516, 158, 100, 100);

        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++)
            frames.add(new TextureRegion(texture, 516 + (i*100), 158, 100, 100));
        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;


        setBounds(getX(), getY(), Ball.BALL_WIDTH / Madball.PPM, Ball.BALL_HEIGHT / Madball.PPM);
//        setRegion(pinkBallTexture);
    }

    public void update(float dTime)
    {
        stateTime += dTime;
        setPosition(box2dBody.getPosition().x - getWidth() / 2, box2dBody.getPosition().y - getHeight() / 2);
        setRegion((TextureRegion) walkAnimation.getKeyFrame(stateTime,true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / Madball.PPM);

        // Set bit category
        fixtureDef.filter.categoryBits = Madball.ENEMY_BIT;
        // What can PINKBALL collide with
        fixtureDef.filter.maskBits = Madball.BALL_BIT
                | Madball.OBJECT_BIT
                | Madball.GROUND_BIT
                | Madball.GOLD_BIT;

        fixtureDef.shape = shape;
        box2dBody.createFixture(fixtureDef);

    }
}
