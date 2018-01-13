package com.chimkontran.madball.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.scenes.Hub;

/**
 * Created by chimkontran on 1/13/2018.
 */

public class Gold extends InteractiveTileObject{
    public Gold(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);

        // Set category bit
        setCategoryFilter(Madball.GOLD_BIT);
    }

    @Override
    public void onGoldCollected() {
        Gdx.app.log("Collision", "Gold");
        Hub.addScore(500);
        setCategoryFilter(Madball.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
