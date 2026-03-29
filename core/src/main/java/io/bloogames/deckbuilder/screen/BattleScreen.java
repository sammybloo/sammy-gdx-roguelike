package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.BaseStats;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.card.*;

public class BattleScreen implements Screen {

    private Main game;
    private Stage stage;

    private Participant player;
    private Participant enemy;

    private Tableau playerTableau;
    private Tableau enemyTableau;
    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(game.getViewport());
        Gdx.input.setInputProcessor(stage);

        player = new Participant(new Color(0.7f, 0.7f, 1f, 1f), 20);
        enemy = new Participant(new Color(1f, 0.7f, 0.7f, 1f), 20);

        playerTableau = new Tableau(5, player, stage);
        playerTableau.setPosition((game.getViewport().getWorldWidth() - playerTableau.getWidth()) / 2, 200);
        stage.addActor(playerTableau);

        enemyTableau = new Tableau(5, enemy, stage, false);
        enemyTableau.setPosition((game.getViewport().getWorldWidth() - enemyTableau.getWidth()) / 2, 560);
        stage.addActor(enemyTableau);

        playerTableau.addBattler(0, new Battler(
            new BattlerModel(
                new BaseBattler("battler", "Mrs Battle", new BaseStats(3, 5))
            )));

        playerTableau.addBattler(2, new Battler(
            new BattlerModel(
                new BaseBattler("beetle", "Buggo", new BaseStats(7, 8))
            )));

        enemyTableau.addBattler(3, new Battler(
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
