package com.chimkontran.madball.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.chimkontran.madball.sprites.InteractiveTileObject;

/**
 * Created by chimkontran on 1/13/2018.
 */

public class WorldContactListener implements ContactListener{
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() == "ball_body" || fixtureB.getUserData() == "ball_body")
        {
            Fixture ball_body = fixtureA.getUserData() == "ball_body" ? fixtureA : fixtureB;
            Fixture object = ball_body == fixtureA ? fixtureB : fixtureA;

            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass()))
            {
                ((InteractiveTileObject) object.getUserData()).onGoldCollected();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
