package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.step.concrete.DrawCardsStep;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.effect.target.concrete.BattlePartyTarget;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.coordinator.CardCoordinator;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleModel implements GameEventPublisher {
    private final BattlePartyModel playerParty;
    private final BattlePartyModel enemyParty;
    private final BattleStateModel battleStateModel;
    private final CardCoordinator cardCoordinator;
    private final GameEventPublisher eventPublisher;

    public BattleModel(BattlePartyModel playerParty, BattlePartyModel enemyParty, GameModel game) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        this.battleStateModel = new BattleStateModel();
        this.cardCoordinator = new CardCoordinator();
        this.eventPublisher = game;

        playerParty.getHand().setDrawFaceUp(true);

        game.getExecutor().begin(
            new EffectBuilder().addTargetStep(TargetType.BATTLE_PARTY, new DrawCardsStep(5)).build(),
            new TargetContext<>(game, null, new BattlePartyTarget(enemyParty)));

        game.getExecutor().begin(
            new EffectBuilder().addTargetStep(TargetType.BATTLE_PARTY, new DrawCardsStep(5)).build(),
            new TargetContext<>(game, null, new BattlePartyTarget(playerParty)));
    }

    public void doNext(EffectExecutor executor) {
        if (executor.hasPending()) {
            executor.update();
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
                return;
            }
            case CARD_ACTIVATING -> {
                battleStateModel.setState(this, BattleState.PLAYER_TURN);
            }
            case FIGHTING -> {
                return;
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

    public void setState(BattleState newState) {
        battleStateModel.setState(this, newState);
    }


    @Override
    public void dispatch(GameEvent event) {
        eventPublisher.dispatch(event);
    }
}
