package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.coordinator.ViewEventBus;
import io.bloogames.deckbuilder.ui.BattleState;
import io.bloogames.deckbuilder.ui.BattleViewState;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class BattleStateController {
    private final ViewEventBus eventBus;
    public boolean targetsOwnCards = false;
    private BattleViewState state = BattleViewState.START_PLAYER_TURN;

    public BattleStateController(ViewEventBus eventBus) {
        this.eventBus = eventBus;

        eventBus.register(ViewEvent.BattleStateEvent.class, event -> changeStateBasedOnModel(event.newState()));
        eventBus.register(ViewEvent.CardStartEvent.class, event -> {
            targetsOwnCards = targetsOwnCards(event.cardSource().card());
            changeState(BattleViewState.CARD_SELECTED);
        });
    }

    private void changeStateBasedOnModel(BattleState battleState) {
        BattleViewState newState = switch (battleState) {
            case BATTLE_START -> BattleViewState.BATTLE_START;
            case START_PLAYER_TURN -> BattleViewState.START_PLAYER_TURN;
            case PLAYER_TURN -> BattleViewState.PLAYER_TURN;
            case CARD_ACTIVATING -> BattleViewState.CARD_ACTIVATING;
            case FIGHTING -> BattleViewState.FIGHTING;
            case ENEMY_TURN -> BattleViewState.ENEMY_TURN;
        };
        changeState(newState);
    }

    public void changeState(BattleViewState newState) {
        BattleViewState oldState = state;
        state = newState;
        eventBus.dispatch(new ViewEvent.BattleViewStateEvent(oldState, newState));
    }

    public boolean canHoverCards() {
        return true;
    }

    public boolean canSelectCards() {
        return state == BattleViewState.PLAYER_TURN
            || (state == BattleViewState.CARD_SELECTED && !targetsOwnCards);
    }

    public boolean canTargetCards() {
        return state == BattleViewState.CARD_SELECTED;
    }

    public boolean canSwapBattlers() {
        return state == BattleViewState.PLAYER_TURN;
    }

    private boolean targetsOwnCards(CardModel card) {
        return card.getBaseCard().getTargetedEffect().targetSpec().allows(TargetType.CARD)
            && card.getBaseCard().getTargetedEffect().targetSpec().ownerType() != TargetOwnerType.OTHER;
    }

}
