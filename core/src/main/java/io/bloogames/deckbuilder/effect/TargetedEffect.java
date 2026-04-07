package io.bloogames.deckbuilder.effect;

import io.bloogames.deckbuilder.effect.target.TargetSpec;

public final class TargetedEffect {
    private final TargetSpec targetSpec;
    private final Effect effect;

    public TargetedEffect(TargetSpec targetSpec, Effect effect) {
        this.targetSpec = targetSpec;
        this.effect = effect;
    }

    public TargetSpec targetSpec() {
        return targetSpec;
    }

    public Effect effect() {
        return effect;
    }
}
