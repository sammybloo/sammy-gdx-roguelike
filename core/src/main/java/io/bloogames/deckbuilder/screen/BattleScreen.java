package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.controller.TargetingController;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.view.*;

public class BattleScreen implements Screen {

    private Main game;
    private Stage stage;

    private GameModel gameModel;
    private BattleModel battleModel;
    private BattleController battleController;

    private PartyView playerParty;
    private PartyView enemyParty;

    private SelectedCardView selectedCardView;

    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(game.getViewport());

        gameModel = new GameModel();

        battleModel = new BattleModel(
            new BattlePartyModel(new PartyModel(new LeaderModel(new BaseLeader("wizard", 20, 5)))),
            new BattlePartyModel(new PartyModel(new LeaderModel(new BaseLeader("villain", 20, 5)))),
            gameModel
        );

        gameModel.setBattle(battleModel);
        battleController = new BattleController(gameModel);

        var arr = new String[]{"battler", "beetle", "bird", "fallenstar", "wrio",
            "vanille", "columbo", "snail", "paulallen", "worms"};
        battleModel.getPlayerParty().getTableau().getSlot(0).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[0])));
        battleModel.getPlayerParty().getTableau().getSlot(2).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[1])));
        battleModel.getEnemyParty().getTableau().getSlot(3).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[2])));

        BattlerCardModel battlerCard;
        for (int i = 0; i < 10; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[MathUtils.random(0, arr.length - 1)]));
            battlerCard.setFaceup(i % 2 != 0);
            battleModel.getEnemyParty().getHand().addCard(battlerCard);
        }

        for (int i = 0; i < 9; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[MathUtils.random(0, arr.length - 1)]));
            battleModel.getPlayerParty().getHand().addCard(battlerCard);
        }
        battleModel.getPlayerParty().getHand().addCard(new ActionCardModel(CardManager.INSTANCE.getActionCard("fireball")));

        playerParty = new PlayerPartyView(battleController, battleModel.getPlayerParty());
        playerParty.setSize(game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        enemyParty = new EnemyPartyView(battleController, battleModel.getEnemyParty());
        enemyParty.setBounds(0, game.getViewport().getWorldHeight() * 0.5f, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        selectedCardView = new SelectedCardView();
        selectedCardView.setBounds(50, (game.getViewport().getWorldHeight() - 450) * 0.5f, 300, 450);
        new TargetingController(battleController, playerParty, enemyParty, selectedCardView);

        stage.addActor(selectedCardView);
        stage.addActor(playerParty);
        stage.addActor(enemyParty);

        Gdx.input.setInputProcessor(new InputMultiplexer(stage));

        playerParty.sync();
    }

    @Override
    public void render(float delta) {
        gameModel.doNext();

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
