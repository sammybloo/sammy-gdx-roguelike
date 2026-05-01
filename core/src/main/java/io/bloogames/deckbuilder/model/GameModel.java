package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventDispatcher;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.effect.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.coordinator.DamageCoordinator;

public class GameModel implements GameEventPublisher {
    private final GameEventDispatcher eventDispatcher;
    private final EffectExecutor executor;
    private BattleModel battle;
    private DamageCoordinator damageCoordinator;
    private Array<Aura> currentAuras = new Array<>();

    public GameModel() {
        this.eventDispatcher = new GameEventDispatcher();
        this.executor = new EffectExecutor();
        this.damageCoordinator = new DamageCoordinator(this);
    }

    public void doNext() {
        if (inBattle()) {
            battle.doNext(executor);
        }
    }

    public BattleModel getBattle() {
        return battle;
    }

    public void setBattle(BattleModel battle) {
        this.battle = battle;
    }

    public boolean inBattle() {
        return battle != null;
    }

    public GameEventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }

    public EffectExecutor getExecutor() {
        return executor;
    }

    public DamageCoordinator getDamageCoordinator() {
        return damageCoordinator;
    }

    public void generateAuras() {
        currentAuras.clear();
        if (inBattle()) {
            battle.addAllAuras(currentAuras);
        }
    }

    public Array<Aura> getAllAuras() {
        return currentAuras;
    }

    @Override
    public void dispatch(GameEvent event) {
        eventDispatcher.dispatch(this, event);
    }
}
