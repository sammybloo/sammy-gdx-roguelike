package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.ui.BattleState;

public class BattleStateModel {
    private BattleState state = BattleState.PLAYER_TURN;

    public BattleState getState() {
        return state;
    }

    public void setState(BattleModel battle, BattleState state) {
        BattleState oldState = this.state;
        this.state = state;
        battle.dispatch(new GameEvent.BattleStateEvent(oldState, state));
    }
}
