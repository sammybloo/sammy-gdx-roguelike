package io.bloogames.deckbuilder.effect.step;

import io.bloogames.deckbuilder.model.ModelProperties;

@FunctionalInterface
public interface StepFactory {
    EffectStep get(ModelProperties properties);
}
