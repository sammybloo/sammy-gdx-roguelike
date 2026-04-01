package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.model.BaseStats;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.ui.Crosshair;
import io.bloogames.deckbuilder.view.*;
import io.bloogames.deckbuilder.model.BaseBattler;
import io.bloogames.deckbuilder.model.BaseCard;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.ParticipantModel;

public class BattleScreen implements Screen {

    private Main game;
    private Stage stage;

    private Participant player;
    private Participant enemy;

    private Tableau playerTableau;
    private Tableau enemyTableau;

    private Hand playerHand;

    private TargetSystem targetSystem;

    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        CommandManager.INSTANCE.setBattle(this);

        stage = new Stage(game.getViewport());

        player = new Participant(new ParticipantModel(new Color(0.7f, 0.7f, 1f, 1f), 20));
        enemy = new Participant(new ParticipantModel(new Color(1f, 0.7f, 0.7f, 1f), 20));

        playerTableau = new Tableau(5, player);
        playerTableau.setPosition((game.getViewport().getWorldWidth() - playerTableau.getWidth()) / 2, 325);
        stage.addActor(playerTableau);

        enemyTableau = new Tableau(5, enemy, false);
        enemyTableau.setPosition((game.getViewport().getWorldWidth() - enemyTableau.getWidth()) / 2, 600);
        stage.addActor(enemyTableau);

        playerTableau.addBattler(0, new Battler(
            new BattlerModel(
                new BaseBattler(
                        new BaseCard("battler", "Mrs Battle", 1),
                    new BaseStats(3, 5))
            )));

        playerTableau.addBattler(2, new Battler(
            new BattlerModel(
                new BaseBattler(
                        new BaseCard("beetle", "Buggo", 2),
                    new BaseStats(7, 8))
            )));

        enemyTableau.addBattler(3, new Battler(
            new BattlerModel(
                new BaseBattler(
                        new BaseCard("bird", "Da Bird", 3),
                    new BaseStats(6, 2))
            )));

        playerHand = new Hand(10);
        playerHand.setBounds(0,-200, game.getViewport().getWorldWidth(), 540);

        for (int i = 0 ; i < 10; i++) {
            var battlerCard = new BattlerCard(new BattlerModel(
                new BaseBattler(
                    new BaseCard("bird", "Da Bird " + i, 3),
                    new BaseStats(6, 2))
            ));
            playerHand.addCard(battlerCard);
        }

        stage.addActor(playerHand);

        targetSystem = new TargetSystem();
        targetSystem.setPosition(0, 0);
        stage.addActor(targetSystem);

        Gdx.input.setInputProcessor(new InputMultiplexer(targetSystem, stage));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);

        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    public Participant getPlayer() {
        return player;
    }

    public Participant getEnemy() {
        return enemy;
    }

    public Tableau getPlayerTableau() {
        return playerTableau;
    }

    public Tableau getEnemyTableau() {
        return enemyTableau;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public TargetSystem getTargetSystem() {
        return targetSystem;
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
