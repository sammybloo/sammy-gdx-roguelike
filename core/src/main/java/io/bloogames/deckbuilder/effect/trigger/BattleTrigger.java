package io.bloogames.deckbuilder.effect.trigger;

import io.bloogames.deckbuilder.effect.event.BattleEvent;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.effect.step.ReactionTiming;

public interface BattleTrigger<E extends BattleEvent> {
    Class<E> eventType();
    ReactionTiming timing();
    void onEvent(E event, EffectExecutor executor);
}
