package com.chimkontran.madball.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chimkontran.madball.Madball;
import com.chimkontran.madball.screens.PlayScreen;

/**
 * Created by chimkontran on 12/30/2017.
 */

public class Controller implements Disposable{
    Viewport viewport;
    Stage stage;
    boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean lookUpPressed, lookDownPressed, lookRightPressed, lookLeftPressed;
    OrthographicCamera camera;

    static final int BUTTON_SIZE = 65;

    public Controller()
    {
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage(viewport, Madball.batch);
        Gdx.input.setInputProcessor(stage);

        // WALK UP button
        Image upImg = new Image(new Texture("upButton.png"));
        upImg.setSize(50, 50);
        upImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = true;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).loop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upPressed = false;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).stop();
            }
        });

        // WALK DOWN button
        Image downImg = new Image(new Texture("downButton.png"));
        downImg.setSize(50, 50);
        downImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = true;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).loop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downPressed = false;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).stop();
            }
        });

        // WALK LEFT button
        Image leftImg = new Image(new Texture("leftButton.png"));
        leftImg.setSize(50, 50);
        leftImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).loop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).stop();
            }
        });

        // WALK RIGHT button
        Image rightImg = new Image(new Texture("rightButton.png"));
        rightImg.setSize(50, 50);
        rightImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).loop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
                Madball.manager.get("audio/ball_walk.wav", Sound.class).stop();
            }
        });

        // LOOK UP button
        Image lookUpImg = new Image(new Texture("shootUpButton.png"));
        lookUpImg.setSize(50, 50);
        lookUpImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                lookUpPressed = true;
                Madball.manager.get("audio/ball_shoot.wav", Sound.class).play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lookUpPressed = false;
            }
        });

        // LOOK DOWN button
        Image lookDownImg = new Image(new Texture("shootDownButton.png"));
        lookDownImg.setSize(50, 50);
        lookDownImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                lookDownPressed = true;
                Madball.manager.get("audio/ball_shoot.wav", Sound.class).play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lookDownPressed = false;
            }
        });

        // LOOK LEFT button
        Image lookLeftImg = new Image(new Texture("shootLeftButton.png"));
        lookLeftImg.setSize(50, 50);
        lookLeftImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                lookLeftPressed = true;
                Madball.manager.get("audio/ball_shoot.wav", Sound.class).play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lookLeftPressed = false;
            }
        });

        // LOOK RIGHT button
        Image lookRightImg = new Image(new Texture("shootRightButton.png"));
        lookRightImg.setSize(50, 50);
        lookRightImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                lookRightPressed = true;
                Madball.manager.get("audio/ball_shoot.wav", Sound.class).play();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                lookRightPressed = false;
            }
        });

        // Create LEFT Controller
        Table table1 = new Table();
        table1.left().bottom();

        table1.add();
        table1.add(upImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table1.add();
        table1.row().pad(5, 5, 5, 5);
        table1.add(leftImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table1.add();
        table1.add(rightImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table1.row().padBottom(5);
        table1.add();
        table1.add(downImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table1.add();
        table1.pack();

        // Display LEFT Controller
        stage.addActor(table1);

        // Create RIGHT Controller
        Table table2 = new Table();
        table2.right().bottom();
        table2.setFillParent(true);

        table2.add();
        table2.add(lookUpImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table2.add();
        table2.row().pad(5, 5, 5, 5);
        table2.add(lookLeftImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table2.add();
        table2.add(lookRightImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table2.row().padBottom(5);
        table2.add();
        table2.add(lookDownImg).size(BUTTON_SIZE, BUTTON_SIZE);
        table2.add();
        table2.pack();

        // Display RIGHT Controller
        stage.addActor(table2);
    }

    public void draw()
    {
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLookUpPressed() {
        return lookUpPressed;
    }

    public boolean isLookDownPressed() {
        return lookDownPressed;
    }

    public boolean isLookRightPressed() {
        return lookRightPressed;
    }

    public boolean isLookLeftPressed() {
        return lookLeftPressed;
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
