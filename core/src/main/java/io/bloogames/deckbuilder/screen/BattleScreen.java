package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.BaseStats;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.card.*;

public class BattleScreen implements Screen {

    private Main game;
    private Stage stage;

    private Tableau playerTableau;
    private Tableau enemyTableau;
    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(game.getViewport());

        playerTableau = new Tableau(5);
        playerTableau.setPosition(0, 200);
        stage.addActor(playerTableau);

        enemyTableau = new Tableau(5);
        enemyTableau.setPosition(0, 700);
        stage.addActor(enemyTableau);

        playerTableau.getSlot(0).setBattler(new Battler(
            new BattlerModel(
                new BaseBattler("battler", "Mrs Battle", new BaseStats(3, 5))
            )));

        enemyTableau.getSlot(3).setBattler(new Battler(
            new BattlerModel(
                new BaseBattler("bird", "Da Bird", new BaseStats(6, 2))
            )));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
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
