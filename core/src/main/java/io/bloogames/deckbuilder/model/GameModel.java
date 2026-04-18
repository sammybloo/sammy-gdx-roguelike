package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventDispatcher;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.execution.EffectExecutor;

public class GameModel implements GameEventPublisher {
    private final GameEventDispatcher eventDispatcher;
    private final EffectExecutor executor;
    private BattleModel battle;

    public GameModel() {
        this.eventDispatcher = new GameEventDispatcher();
        this.executor = new EffectExecutor();
    }

    public void doNext() {
        if (battle != null) {
            battle.doNext(executor);
        }
    }

    public BattleModel getBattle() {
        return battle;
    }

    public void setBattle(BattleModel battle) {
        this.battle = battle;
    }

    public GameEventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public EffectExecutor getExecutor() {
        return executor;
    }

    @Override
    public void dispatch(GameEvent event) {
        eventDispatcher.dispatch(this, event);
    }
}
