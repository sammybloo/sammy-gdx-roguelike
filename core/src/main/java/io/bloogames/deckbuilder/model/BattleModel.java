package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.number.ExactAmount;
import io.bloogames.deckbuilder.effect.source.concrete.GameRuleSource;
import io.bloogames.deckbuilder.effect.step.concrete.DrawCardsStep;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.concrete.BattlePartyTarget;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.coordinator.BattleCleanupCoordinator;
import io.bloogames.deckbuilder.model.coordinator.CardCoordinator;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleModel implements GameEventPublisher {
    private final BattlePartyModel playerParty;
    private final BattlePartyModel enemyParty;
    private final BattleStateModel battleStateModel;
    private final CardCoordinator cardCoordinator;
    private final BattleCleanupCoordinator cleanupCoordinator;
    private final GameEventPublisher eventPublisher;

    public BattleModel(BattlePartyModel playerParty, BattlePartyModel enemyParty, GameModel game) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        this.battleStateModel = new BattleStateModel();
        this.cardCoordinator = new CardCoordinator(game);
        this.cleanupCoordinator = new BattleCleanupCoordinator(game);
        this.eventPublisher = game;

        playerParty.getHand().setAddFaceUp(true);

        game.getExecutor().begin(
            new EffectBuilder().addTargetStep(TargetType.BATTLE_PARTY, (modelProperties -> new DrawCardsStep(new ExactAmount(5)))).build(),
            new TargetContext<>(game, new GameRuleSource(game), new BattlePartyTarget(enemyParty)));

        game.getExecutor().begin(
            new EffectBuilder().addTargetStep(TargetType.BATTLE_PARTY, modelProperties -> new DrawCardsStep(new ExactAmount(10))).build(),
            new TargetContext<>(game, new GameRuleSource(game), new BattlePartyTarget(playerParty)));
    }

    public void doNext(EffectExecutor executor) {
        if (executor.hasPending()) {
            cleanupCoordinator.beforeEachStep();
            executor.update();
            return;
        }

        if (cleanupCoordinator.beforePhase()) {
            return;
        }

        switch (battleStateModel.getState()) {
            case BATTLE_START -> {
                battleStateModel.setState(this, BattleState.ENEMY_TURN);
            }
            case START_PLAYER_TURN -> {
                battleStateModel.setState(this, BattleState.PLAYER_TURN);
            }
            case PLAYER_TURN -> {
            }
            case CARD_ACTIVATING -> {
                battleStateModel.setState(this, BattleState.PLAYER_TURN);
            }
            case FIGHTING -> {
            }
            case ENEMY_TURN -> {
                battleStateModel.setState(this, BattleState.START_PLAYER_TURN);
            }

        }
    }

    public BattlePartyModel getPlayerParty() {
        return playerParty;
    }

    public BattlePartyModel getEnemyParty() {
        return enemyParty;
    }

    public CardCoordinator getCardCoordinator() {
        return cardCoordinator;
    }

    public BattleStateModel getBattleStateModel() {
        return battleStateModel;
    }

    public BattlePartyModel getOwner(CardModel cardModel) {
        if (playerParty.hasCard(cardModel)) {
            return playerParty;
        } else if (enemyParty.hasCard(cardModel)) {
            return enemyParty;
        }
        return null;
    }

    public BattlePartyModel getParty(Ownership.Type owner) {
        if (owner == Ownership.Type.PLAYER) {
            return playerParty;
        }
        if (owner == Ownership.Type.ENEMY) {
            return enemyParty;
        }
        return null;
    }

    public void addAllAuras(Array<AuraModel> arr) {
        playerParty.addAllAuras(arr);
        enemyParty.addAllAuras(arr);
    }

    public void setState(BattleState newState) {
        battleStateModel.setState(this, newState);
    }


    @Override
    public void dispatch(GameEvent event) {
        eventPublisher.dispatch(event);
    }
}
