package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.source.concrete.BattlerCardSource;
import io.bloogames.deckbuilder.effect.target.concrete.SlotTarget;
import io.bloogames.deckbuilder.manager.CardManager;
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

        var arr = new String[]{"battler", "beetle", "bird", "fallenstar", "wrio",
            "vanille", "columbo", "snail", "paulallen", "worms"};
        battleModel.getPlayerParty().getTableau().getSlot(0).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[0])));
        battleModel.getPlayerParty().getTableau().getSlot(2).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[1])));
        battleModel.getEnemyParty().getTableau().getSlot(3).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[2])));

        BattlerCardModel battlerCard = null;
        for (int i = 0; i < 10; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[MathUtils.random(0, arr.length - 1)]));
            battleModel.getEnemyParty().getHand().addCard(battlerCard);
        }

        for (int i = 0; i < 10; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[MathUtils.random(0, arr.length - 1)]));
            battleModel.getPlayerParty().getHand().addCard(battlerCard);
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

        EffectExecutor executor = new EffectExecutor();
        TargetedEffect effect = battlerCard.getBaseCard().getEffect();
        TargetContext<SlotTarget> context = new TargetContext<>(battleModel, new BattlerCardSource(battlerCard, battleModel.getPlayerParty()),
            new SlotTarget(battleModel.getPlayerParty().getTableau().getSlot(1), battleModel.getPlayerParty()));
        executor.begin(effect.effect(), context);
        executor.update();
        battleModel.getPlayerParty().getHand().removeCard(battlerCard);

        playerParty.update();
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
