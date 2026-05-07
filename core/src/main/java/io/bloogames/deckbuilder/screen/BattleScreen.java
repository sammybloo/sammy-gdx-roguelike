package io.bloogames.deckbuilder.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.tommyettinger.colorful.rgb.ColorfulBatch;
import io.bloogames.deckbuilder.Main;
import io.bloogames.deckbuilder.controller.BattleController;
import io.bloogames.deckbuilder.controller.EnemyPartyController;
import io.bloogames.deckbuilder.controller.PlayerPartyController;
import io.bloogames.deckbuilder.controller.TargetingController;
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

        SeedManager.INSTANCE.setSeed("COLUMBO");

        gameModel = new GameModel();

        battleModel = new BattleModel(
            new BattlePartyModel(new PartyModel(new LeaderModel(
                new BaseLeader("wizard", 20, 7, AuraSupplier.empty), Ownership.Type.PLAYER))),
            new BattlePartyModel(new PartyModel(new LeaderModel(
                new BaseLeader("villain", 20, 8, AuraSupplier.empty), Ownership.Type.ENEMY))),
            gameModel
        );

        gameModel.setBattle(battleModel);

        var arr = new String[]{"beetle", "beetle", "beetle", "battler", "battler",
            "bird", "grow", "grow", "fireball", "fireball"};

        BattlerCardModel battlerCard;

        for (String s : arr) {
            battleModel.getPlayerParty().getDeck().addCard(
                CardManager.INSTANCE.getCardModel(s, battleModel.getPlayerParty().getOwnership().getCurrentOwner())
            );
        }

        battleModel.getPlayerParty().getDeck().shuffle();


        for (String s : arr) {
            battleModel.getEnemyParty().getDeck().addCard(
                CardManager.INSTANCE.getCardModel(s, battleModel.getEnemyParty().getOwnership().getCurrentOwner())
            );
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


        battleController.getEventBus().register(ViewEvent.BattleViewStateEvent.class, e -> {
            playerParty.sync();
            enemyParty.sync();
        });

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
