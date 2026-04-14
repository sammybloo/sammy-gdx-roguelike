package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.effect.trigger.ViewEventListener;
import io.bloogames.deckbuilder.model.coordinator.ViewEventBus;
import io.bloogames.deckbuilder.ui.BattleState;
import io.bloogames.deckbuilder.ui.BattleViewState;
import io.bloogames.deckbuilder.view.event.ViewEvent;

public class BattleStateController {
    private final ViewEventBus eventBus;
    private BattleViewState state = BattleViewState.PLAYER_TURN;

    public BattleStateController(ViewEventBus eventBus) {
        this.eventBus = eventBus;

        eventBus.register(ViewEvent.BattleStateEvent.class, event -> changeStateBasedOnModel(event.newState()));
    }

    private void changeStateBasedOnModel(BattleState battleState) {
        BattleViewState newState = switch (battleState) {
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
        return state == BattleViewState.PLAYER_TURN;
    }

    public boolean canSelectCards() {
        return state == BattleViewState.PLAYER_TURN;
    }

    public boolean canTargetCards() {
        return state == BattleViewState.CARD_SELECTED;
    }

    public boolean canSwapBattlers() {
        return state == BattleViewState.PLAYER_TURN;
    }

}
