package io.bloogames.deckbuilder.effect;

import io.bloogames.deckbuilder.effect.step.StepFactory;
import io.bloogames.deckbuilder.effect.target.TargetType;

public sealed interface EffectStepEntry permits EffectStepEntry.Battle, EffectStepEntry.Target {
    record Battle(StepFactory stepSupplier) implements EffectStepEntry {
    }

    record Target(TargetType targetType, StepFactory stepSupplier) implements EffectStepEntry {
    }
}
