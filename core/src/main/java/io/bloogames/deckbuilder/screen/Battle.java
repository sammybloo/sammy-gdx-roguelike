package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.data.*;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.view.*;

public class Battle implements Screen {

    private Main game;
    private Stage stage;

    private BattleModel battleModel;

    private PartyView playerParty;
    private PartyView enemyParty;

    private TargetSystemView targetSystem;

    public Battle(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        CommandManager.INSTANCE.setBattle(battleModel);

        stage = new Stage(game.getViewport());

        battleModel = new BattleModel(
            new PartyModel(new LeaderModel(new BaseLeader("wizard", 20, 5)),
                new TableauModel(5), new HandModel(10)),
            new PartyModel(new LeaderModel(new BaseLeader("villain", 20, 5)),
                new TableauModel(5), new HandModel(10))
        );

        var arr = new BaseBattler[]{
            new BaseBattler(
                new BaseBattlerCard("battler", "Mrs Battle", 1,
                    new BaseStats(3, 5))),
            new BaseBattler(
                new BaseBattlerCard("beetle", "Buggo", 2,
                    new BaseStats(7, 8))),
            new BaseBattler(
                new BaseBattlerCard("bird", "Da Bird", 3,
                    new BaseStats(6, 2))),
            new BaseBattler(
                new BaseBattlerCard("fallenstar", "Fallen Star", 3,
                    new BaseStats(99, 99))),
            new BaseBattler(
                new BaseBattlerCard("wrio", "Warm Wriothesley", 3,
                    new BaseStats(6, 0))),
            new BaseBattler(
                new BaseBattlerCard("vanille", "Lesbean", 3,
                new BaseStats(3, 10))),
            new BaseBattler(
                new BaseBattlerCard("columbo", "Columbno Glasses", 3,
                new BaseStats(5, 5))),
            new BaseBattler(
                new BaseBattlerCard("snail", "Snaul", 3,
                new BaseStats(12, 1))),
            new BaseBattler(
                new BaseBattlerCard("paulallen", "Harvey Normal", 3,
                new BaseStats(2, 2))),
            new BaseBattler(
                new BaseBattlerCard("worms", "Vent Worms", 3,
                new BaseStats(4, 5)))
        };
        battleModel.getPlayerParty().getTableau().getSlot(0).setBattler(new BattlerModel(arr[0]));
        battleModel.getPlayerParty().getTableau().getSlot(2).setBattler(new BattlerModel(arr[1]));
        battleModel.getEnemyParty().getTableau().getSlot(3).setBattler(new BattlerModel(arr[2]));

        for (int i = 0; i < 10; i++) {
            var battlerCard = new BattlerCardModel(
                arr[MathUtils.random(0, arr.length - 1)].getBaseCard());
            battleModel.getPlayerParty().getHand().addCard(battlerCard);
        }

        for (int i = 0; i < 10; i++) {
            var battlerCard = new BattlerCardModel(
                arr[MathUtils.random(0, arr.length - 1)].getBaseCard());
            battleModel.getEnemyParty().getHand().addCard(battlerCard);
        }

        targetSystem = new TargetSystemView();
        targetSystem.setPosition(0, 0);
        stage.addActor(targetSystem);

        playerParty = new PlayerPartyView(battleModel.getPlayerParty());
        playerParty.setBounds(0, 0, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        enemyParty = new EnemyPartyView(battleModel.getEnemyParty());
        enemyParty.setBounds(0, game.getViewport().getWorldHeight() * 0.5f, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        stage.addActor(playerParty);
        stage.addActor(enemyParty);

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

    public TableauView getEnemyTableau() {
        return enemyParty.getTableau();
    }

    public HandView getPlayerHand() {
        return playerParty.getHand();
    }

    public TableauView getPlayerTableau() {
        return playerParty.getTableau();
    }

    public TargetSystemView getTargetSystem() {
        return targetSystem;
    }

    @Override
    public void resize(int width, int height) {
        playerParty.setBounds(0, 0, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);
        enemyParty.setBounds(0, game.getViewport().getWorldHeight() * 0.5f, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);
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
