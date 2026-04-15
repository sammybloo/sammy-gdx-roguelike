package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.model.coordinator.CardCoordinator;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleModel implements GameEventPublisher {
    private final BattlePartyModel playerParty;
    private final BattlePartyModel enemyParty;
    private final BattleStateModel battleStateModel;
    private final CardCoordinator cardCoordinator;
    private final GameEventPublisher eventPublisher;

    public BattleModel(BattlePartyModel playerParty, BattlePartyModel enemyParty, GameEventPublisher eventPublisher) {
        this.playerParty = playerParty;
        this.enemyParty = enemyParty;
        this.battleStateModel = new BattleStateModel();
        this.cardCoordinator = new CardCoordinator();
        this.eventPublisher = eventPublisher;
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
