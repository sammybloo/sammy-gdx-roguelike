package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.controller.EnemyHandController;
import io.bloogames.deckbuilder.controller.HandController;
import io.bloogames.deckbuilder.controller.PlayerHandController;
import io.bloogames.deckbuilder.data.BaseBattler;
import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.scene2d.FannedGroup;
import io.bloogames.deckbuilder.view.*;

public class BattleScreen implements Screen {

    private Main game;
    private Stage stage;

    private Leader player;
    private Leader enemy;
    private Tableau playerTableau;
    private Tableau enemyTableau;

    private Hand playerHand;
    private Hand enemyHand;

    private TargetSystem targetSystem;

    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        CommandManager.INSTANCE.setBattle(this);

        stage = new Stage(game.getViewport());

        player = new Leader(new LeaderModel(new BaseLeader(new Color(0.7f, 0.7f, 1f, 1f), 20)));
        enemy = new Leader(new LeaderModel(new BaseLeader(new Color(1f, 0.7f, 0.7f, 1f), 20)));

        playerTableau = new Tableau(new TableauModel(5, player.getModel()));
        playerTableau.setPosition((game.getViewport().getWorldWidth() - playerTableau.getWidth()) / 2, 325);
        stage.addActor(playerTableau);

        enemyTableau = new Tableau(new TableauModel(5, enemy.getModel()), false);
        enemyTableau.setPosition((game.getViewport().getWorldWidth() - enemyTableau.getWidth()) / 2, 600);
        stage.addActor(enemyTableau);

        var arr = new BaseBattler[] {
            new BaseBattler(
                new BaseCard("battler", "Mrs Battle", 1),
                new BaseStats(3, 5)),
            new BaseBattler(
                new BaseCard("beetle", "Buggo", 2),
                new BaseStats(7, 8)),
            new BaseBattler(
                new BaseCard("bird", "Da Bird", 3),
                new BaseStats(6, 2))
        };

        playerTableau.addBattler(0, new Battler(new BattlerModel(arr[0])));

        playerTableau.addBattler(2, new Battler(new BattlerModel(arr[1])));

        enemyTableau.addBattler(3, new Battler(new BattlerModel(arr[2])));

        playerHand = new Hand(new HandModel(10),
            new FannedGroup.FanSettings(0.3f, 80f, 16f, 0.5f, 0.8f,
                130f, 0f, 0.3f, Card.WIDTH, Card.HEIGHT),
            new PlayerHandController());
        playerHand.setBounds(0,-200, game.getViewport().getWorldWidth(), 540);

        for (int i = 0 ; i < 10; i++) {
            var battlerCard = new BattlerCard(new BattlerModel(
                arr[MathUtils.random(0, 2)]
            ));
            playerHand.addCard(battlerCard);
        }

        stage.addActor(playerHand);

        enemyHand = new Hand(new HandModel(10),
            new FannedGroup.FanSettings(0.3f, 80f, 16f, 0.5f, 0.8f,
                240f, 180f, 0.2f, Card.WIDTH, Card.HEIGHT),
            new EnemyHandController());
        enemyHand.setBounds(1920,1380, game.getViewport().getWorldWidth(), 540);
        enemyHand.setRotation(180);

        for (int i = 0 ; i < 5; i++) {
            var battlerCard = new BattlerCard(new BattlerModel(
                arr[MathUtils.random(0, 2)]
            ));
            if (i % 2 != 0) battlerCard.flipCard(false);
            enemyHand.addCard(battlerCard);
        }

        stage.addActor(enemyHand);

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

    public Leader getPlayer() {
        return player;
    }

    public Leader getEnemy() {
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
