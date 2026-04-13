package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventDispatcher;
import io.bloogames.deckbuilder.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.coordinator.CardCoordinator;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleModel {
    private final PartyModel playerParty;
    private final PartyModel enemyParty;
    private final GameEventDispatcher eventDispatcher;
    private final EffectExecutor executor;
    private final BattleStateModel battleStateModel;
    private final CardCoordinator cardCoordinator;

    public BattleModel(PartyModel playerParty, PartyModel enemyParty) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        this.eventDispatcher = new GameEventDispatcher();
        this.executor = new EffectExecutor();
        this.battleStateModel = new BattleStateModel();
        this.cardCoordinator = new CardCoordinator();
    }

    public PartyModel getPlayerParty() {
        return playerParty;
    }

    public PartyModel getEnemyParty() {
        return enemyParty;
    }

    public GameEventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public EffectExecutor getExecutor() {
        return executor;
    }

    public CardCoordinator getCardCoordinator() {
        return cardCoordinator;
    }

    public BattleStateModel getBattleStateModel() {
        return battleStateModel;
    }

    public PartyModel getOwner(CardModel cardModel) {
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

    public void dispatch(GameEvent event) {
        eventDispatcher.dispatch(this, event);
    }
}
