package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.TargetType;

public final class Effect {
    private final Array<EffectStepEntry> entries;

    public Effect(Array<EffectStepEntry> entries) {
        this.entries = new Array<>(entries);
    }

    public Array<EffectStep> stepsFor(TargetType targetType) {
        Array<EffectStep> filtered = new Array<>();

        for (int i = 0; i < entries.size; i++) {
            EffectStepEntry entry = entries.get(i);

            if (entry instanceof EffectStepEntry.Battle(EffectStep step)) {
                filtered.add(step);
                continue;
            }

            EffectStepEntry.Target target = (EffectStepEntry.Target) entry;
            if (target.targetType() == targetType) {
                filtered.add(target.step());
            }
        }

        return filtered;
    }
}
