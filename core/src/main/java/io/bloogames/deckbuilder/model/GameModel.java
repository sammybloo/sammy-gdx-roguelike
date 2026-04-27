package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.event.GameEventDispatcher;
import io.bloogames.deckbuilder.event.GameEventPublisher;
import io.bloogames.deckbuilder.execution.EffectExecutor;
import io.bloogames.deckbuilder.model.aura.Aura;

public class GameModel implements GameEventPublisher {
    private final GameEventDispatcher eventDispatcher;
    private final EffectExecutor executor;
    private BattleModel battle;

    public GameModel() {
        this.eventDispatcher = new GameEventDispatcher();
        this.executor = new EffectExecutor();
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

    public Array<Aura> getAllAuras() {
        Array<Aura> result = new Array<>();
        if (inBattle()) {
            battle.addAllAuras(result);
        }
        return result;
    }

    @Override
    public void dispatch(GameEvent event) {
        eventDispatcher.dispatch(this, event);
    }
}
