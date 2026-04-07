package io.bloogames.deckbuilder.effect.execution;

import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.EffectContext;
import io.bloogames.deckbuilder.effect.target.Target;

public final class EffectExecution<T extends Target> {
    private final Effect effect;
    private final EffectContext<T> context;
    private int battleIndex;
    private int targetIndex;
    private boolean battlePhaseDone;

    public EffectExecution(Effect effect, EffectContext<T> context) {
        this.effect = effect;
        this.context = context;
    }

    public Effect effect() {
        return effect;
    }

    public EffectContext<T> context() {
        return context;
    }

    public int battleIndex() {
        return battleIndex;
    }

    public void battleIndex(int battleIndex) {
        this.battleIndex = battleIndex;
    }

    public int targetIndex() {
        return targetIndex;
    }

    public void targetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public boolean battlePhaseDone() {
        return battlePhaseDone;
    }

    public void battlePhaseDone(boolean battlePhaseDone) {
        this.battlePhaseDone = battlePhaseDone;
    }
}
