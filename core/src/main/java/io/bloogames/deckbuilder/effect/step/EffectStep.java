package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.text.DescriptionProperties;

public interface EffectStep {
    void apply(TargetContext<? extends Target> context);

    default void registerProperties(DescriptionProperties properties) {
    }
}
