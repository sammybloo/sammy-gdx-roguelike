package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.Target;

public interface GameStep extends EffectStep {
    @Override
    void apply(TargetContext<? extends Target> context);
}
