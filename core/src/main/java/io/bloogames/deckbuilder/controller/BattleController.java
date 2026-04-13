package io.bloogames.deckbuilder.controller;

import io.bloogames.deckbuilder.model.BattleModel;
import io.bloogames.deckbuilder.model.coordinator.ViewEventBus;

public class BattleController {
    private final BattleModel battle;
    private final ViewEventBus eventBus;
    private final BattleStateController battleState;

    public BattleController(BattleModel battle) {
        this.battle = battle;
        this.eventBus = new ViewEventBus(battle.getEventDispatcher());
        this.battleState = new BattleStateController(eventBus);
    }

    public BattleModel getBattle() {
        return battle;
    }

    public ViewEventBus getEventBus() {
        return eventBus;
    }

    public BattleStateController getBattleState() {
        return battleState;
    }
}
