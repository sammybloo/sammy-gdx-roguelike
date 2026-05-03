package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.tommyettinger.colorful.rgb.ColorfulBatch;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.controller.*;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseLeader;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.manager.SeedManager;
import io.bloogames.deckbuilder.model.*;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.view.*;

public class BattleScreen implements Screen {

    private final Main game;
    private Stage stage;

    private GameModel gameModel;
    private BattleModel battleModel;
    private BattleController battleController;

    private PlayerPartyView playerParty;
    private EnemyPartyView enemyParty;

    private SelectedCardView selectedCardView;

    public BattleScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(game.getViewport(), new ColorfulBatch());

        SeedManager.INSTANCE.setSeed("WUMBO");

        gameModel = new GameModel();

        battleModel = new BattleModel(
            new BattlePartyModel(new PartyModel(new LeaderModel(
                new BaseLeader("wizard", 20, 7, AuraSupplier.empty), Ownership.Type.PLAYER))),
            new BattlePartyModel(new PartyModel(new LeaderModel(
                new BaseLeader("villain", 20, 8, AuraSupplier.empty), Ownership.Type.ENEMY))),
            gameModel
        );

        gameModel.setBattle(battleModel);

        var arr = new String[]{"battler", "beetle", "bird", "fallenstar", "wrio",
            "vanille", "columbo", "snail", "paulallen", "worms"};
        battleModel.getPlayerParty().getTableau().getSlot(0).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[1]), Ownership.Type.PLAYER));
        battleModel.getPlayerParty().getTableau().getSlot(2).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[0]), Ownership.Type.PLAYER));
        battleModel.getEnemyParty().getTableau().getSlot(3).setBattler(
            new BattlerModel(CardManager.INSTANCE.getBattlerCard(arr[2]), Ownership.Type.ENEMY));

        BattlerCardModel battlerCard;

        for (int i = 0; i < 20; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[i % arr.length]), Ownership.Type.PLAYER);
            battleModel.getPlayerParty().getDeck().addCard(battlerCard);
        }
        battleModel.getPlayerParty().getDeck().shuffle();
        battleModel.getPlayerParty().getDeck().addCard(
            new ActionCardModel(CardManager.INSTANCE.getActionCard("fireball"), Ownership.Type.PLAYER));
        battleModel.getPlayerParty().getDeck().addCard(
            new ActionCardModel(CardManager.INSTANCE.getActionCard("fireball"), Ownership.Type.PLAYER));
        battleModel.getPlayerParty().getDeck().addCard(
            new ActionCardModel(CardManager.INSTANCE.getActionCard("grow"), Ownership.Type.PLAYER));

        for (int i = 0; i < 45; i++) {
            battlerCard = new BattlerCardModel(
                CardManager.INSTANCE.getBattlerCard(arr[i % arr.length]), Ownership.Type.ENEMY);
            battleModel.getEnemyParty().getDeck().addCard(battlerCard);
        }
        battleModel.getEnemyParty().getDeck().shuffle();

        battleController = new BattleController(gameModel);

        playerParty = new PlayerPartyView(battleController, battleModel.getPlayerParty());
        playerParty.setSize(game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        new PlayerPartyController(playerParty, battleController);

        enemyParty = new EnemyPartyView(battleController, battleModel.getEnemyParty());
        enemyParty.setBounds(0, game.getViewport().getWorldHeight() * 0.5f, game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight() * 0.5f);

        new EnemyPartyController(enemyParty, battleController);

        selectedCardView = new SelectedCardView();
        selectedCardView.setBounds(50, (game.getViewport().getWorldHeight() - 450) * 0.5f, 300, 450);

        new TargetingController(battleController, playerParty, enemyParty, selectedCardView);

        stage.addActor(selectedCardView);
        stage.addActor(enemyParty);
        stage.addActor(playerParty);

        VFXManager.INSTANCE.initialiseForStage(stage);
        Gdx.input.setInputProcessor(new InputMultiplexer(stage));

        playerParty.sync();


    }

    @Override
    public void render(float delta) {
        if (VFXManager.INSTANCE.isReady()) {
            gameModel.doNext();
        }

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
        selectedCardView.setBounds(50, (game.getViewport().getWorldHeight() - 450) * 0.5f, 300, 450);
        VFXManager.INSTANCE.resize(game.getViewport().getWorldWidth(), game.getViewport().getWorldHeight());
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
