package com.chimkontran.madball.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.chimkontran.madball.Madball;

/**
 * Created by chimkontran on 12/13/2017.
 */

public class Hub implements Disposable{
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Integer level;

    Label levelTextLabel;
    Label levelLabel;
    Label scoreTextLabel;
    Label scoreLabel;
    Label countdownTextLabel;
    Label countdownLabel;

    public Hub(SpriteBatch spriteBatch){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        level = 1;

        viewport = new FitViewport(Madball.V_WIDTH, Madball.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        // Create table
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        levelTextLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        countdownTextLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreTextLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel = new Label(String.format("%01d", level), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        // Add table
        table.add(levelTextLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.add(countdownTextLabel).expandX().padTop(10);
        table.row();
        table.add(levelLabel).expandX();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
