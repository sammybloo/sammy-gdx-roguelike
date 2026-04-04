package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.data.BaseBattler;
import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.view.*;

public class Battle implements Screen {

    private Main game;
    private Stage stage;

    private BattleModel battleModel;

    private Party playerParty;
    private Party enemyParty;

    private TargetSystem targetSystem;

    public Battle(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        CommandManager.INSTANCE.setBattle(this);

        stage = new Stage(game.getViewport());

        battleModel = new BattleModel(
            new PartyModel(new LeaderModel(new BaseLeader("wizard", 20)),
                new TableauModel(5), new HandModel(10)),
            new PartyModel(new LeaderModel(new BaseLeader("villain", 20)),
                new TableauModel(5), new HandModel(10))
        );

        playerParty = new PlayerParty(battleModel.getPlayerParty());
        playerParty.setBounds(0, 0, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        enemyParty = new EnemyParty(battleModel.getEnemyParty());
        enemyParty.setBounds(0, game.getViewport().getWorldHeight() * 0.5f, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        var arr = new BaseBattler[]{
            new BaseBattler(
                new BaseCard("battler", "Mrs Battle", 1),
                new BaseStats(3, 5)),
            new BaseBattler(
                new BaseCard("beetle", "Buggo", 2),
                new BaseStats(7, 8)),
            new BaseBattler(
                new BaseCard("bird", "Da Bird", 3),
                new BaseStats(6, 2)),
            new BaseBattler(
                new BaseCard("fallenstar", "Fallen Star", 3),
                new BaseStats(99, 99)),
            new BaseBattler(
                new BaseCard("wrio", "Warm Wriothesley", 3),
                new BaseStats(6, 0)),
            new BaseBattler(
                new BaseCard("vanille", "Lesbean", 3),
                new BaseStats(3, 10)),
            new BaseBattler(
                new BaseCard("columbo", "Columbno Glasses", 3),
                new BaseStats(5, 5)),
            new BaseBattler(
                new BaseCard("snail", "Snaul", 3),
                new BaseStats(12, 1)),
            new BaseBattler(
                new BaseCard("paulallen", "Harvey Normal", 3),
                new BaseStats(2, 2)),
            new BaseBattler(
                new BaseCard("worms", "Vent Worms", 3),
                new BaseStats(4, 5))
        };
        playerParty.getTableau().addBattler(0, new BattlerModel(arr[0]));
        enemyParty.getTableau().addBattler(3, new BattlerModel(arr[2]));

        for (int i = 0; i < 10; i++) {
            var battlerCard = new BattlerCard(new BattlerModel(
                arr[MathUtils.random(0, arr.length - 1)]
            ));
            playerParty.getHand().addCard(battlerCard);
        }

        for (int i = 0; i < 10; i++) {
            var battlerCard = new BattlerCard(new BattlerModel(
                arr[MathUtils.random(0, arr.length - 1)]
            ));
            if (i % 2 != 0) battlerCard.flipCard(false);
            enemyParty.getHand().addCard(battlerCard);
        }

        stage.addActor(playerParty);
        stage.addActor(enemyParty);

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


    public Tableau getEnemyTableau() {
        return enemyParty.getTableau();
    }

    public Hand getPlayerHand() {
        return playerParty.getHand();
    }

    public Tableau getPlayerTableau() {
        return playerParty.getTableau();
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
