package com.chimkontran.madball.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.chimkontran.madball.Madball;

/**
 * Created by chimkontran on 12/14/2017.
 */

public class Ball extends Sprite{
    public World world;
    public Body box2dBody;

    public Ball(World world) {
        this.world = world;
        defineBall();
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
