package io.bloogames.deckbuilder.model.coordinator;

import io.bloogames.deckbuilder.effect.event.GameEventDispatcher;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.BattleModel;

public class BattleCoordinator {
    private final GameEventDispatcher eventDispatcher = new GameEventDispatcher();
    private final EffectExecutor executor = new EffectExecutor(eventDispatcher);
    private final CardCoordinator cardCoordinator;
    private final BattleStateCoordinator interactionManager = new BattleStateCoordinator(executor);
    private final BattleModel battle;

    public BattleCoordinator(BattleModel battle) {
        this.battle = battle;
        cardCoordinator = new CardCoordinator(executor, battle);
    }

    public EffectExecutor getExecutor() {
        return executor;
    }

    public CardCoordinator getCardCoordinator() {
        return cardCoordinator;
    }

    public BattleStateCoordinator getInteractionManager() {
        return interactionManager;
    }

    public GameEventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
