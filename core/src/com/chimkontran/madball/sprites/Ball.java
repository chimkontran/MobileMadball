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

/**
 * Created by chimkontran on 12/14/2017.
 */

public class Ball extends Sprite{
    public World world;
    public Body box2dBody;
    public TextureRegion ballTexture;

    public Ball(World world, PlayScreen screen) {
        super(screen.getTextureAtlas().findRegion("swat"));
        this.world = world;
        defineBall();

        // Get texture for Ball (player)
        ballTexture = new TextureRegion(getTexture(),715, 27, 100, 100);
        setBounds(0, 0, 30 / Madball.PPM, 30 / Madball.PPM);
        setRegion(ballTexture);
    }

    // Update Ball location
    public void update(float dTime)
    {
        setPosition(box2dBody.getPosition().x - getWidth() / 2, box2dBody.getPosition().y - getHeight() / 2);
    }

    private void defineBall() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(64 / Madball.PPM, 64 / Madball.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2dBody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / Madball.PPM);

        fixtureDef.shape = shape;
        box2dBody.createFixture(fixtureDef);

    }
}
