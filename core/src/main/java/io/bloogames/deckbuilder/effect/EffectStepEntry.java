package io.bloogames.deckbuilder.effect;

import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.TargetType;

public sealed interface EffectStepEntry permits EffectStepEntry.Battle, EffectStepEntry.Target {
    record Battle(EffectStep step) implements EffectStepEntry {
    }

    record Target(TargetType targetType, EffectStep step) implements EffectStepEntry {
    }
}
