package io.bloogames.deckbuilder.model.coordinator;

import io.bloogames.deckbuilder.effect.event.BattleStateEvent;
import io.bloogames.deckbuilder.effect.event.CardPlayedEvent;
import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.trigger.GameEventTrigger;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleStateCoordinator {
    private BattleState state = BattleState.PLAYER_TURN;

    public BattleStateCoordinator(EffectExecutor executor) {
        executor.addSubscriber(new GameEventTrigger<CardPlayedEvent>() {
            @Override
            public Class<CardPlayedEvent> eventType() {
                return CardPlayedEvent.class;
            }

            @Override
            public void onEvent(CardPlayedEvent event, EffectExecutor executor) {
                BattleState oldState = state;
                state = BattleState.CARD_ACTIVATING;
                executor.emit(new BattleStateEvent(event.battle(), oldState, state));
            }
        });
    }
    public BattleState getState() {
        return state;
    }

    public void setState(BattleState state) {
        this.state = state;
    }

    public boolean canHoverCards() {
        return state == BattleState.PLAYER_TURN;
    }

    public boolean canSelectCards() {
        return state == BattleState.PLAYER_TURN;
    }

    public boolean canTargetCards() {
        return state == BattleState.CARD_SELECTED;
    }
}
