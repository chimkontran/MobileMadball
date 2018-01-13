package com.chimkontran.madball.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.sprites.Gold;

/**
 * Created by chimkontran on 12/22/2017.
 */

public class Box2DWorldCreator {

    public Box2DWorldCreator(World world, TiledMap map)
    {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // Create GROUND/WALL Body / Fixture
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Madball.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Madball.PPM);
            body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth() / 2 / Madball.PPM, rectangle.getHeight() / 2 / Madball.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        // Create GOLD Body / Fixture
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            new Gold(world, map, rectangle);
        }
    }

}
