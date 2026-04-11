package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.event.GameEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.trigger.GameEventListener;
import io.bloogames.deckbuilder.effect.trigger.GameEventTrigger;
import io.bloogames.deckbuilder.model.BattleModel;

public class BattleCoordinator {
    EffectExecutor executor = new EffectExecutor();
    CardCoordinator cardCoordinator = new CardCoordinator();
    EventAdapter eventAdapter = new EventAdapter();
    BattleModel battle;

    public BattleCoordinator(BattleModel battle) {
        this.battle = battle;
        executor.addSubscriber(eventAdapter);
    }

    public EffectExecutor getExecutor() {
        return executor;
    }

    public CardCoordinator getCardCoordinator() {
        return cardCoordinator;
    }

    public EventAdapter getEventAdapter() {
        return eventAdapter;
    }
}
