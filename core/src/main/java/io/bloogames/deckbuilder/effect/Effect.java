package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.step.EffectStep;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.ModelProperties;

public final class Effect {
    private final Array<EffectStepEntry> entries;

    public Effect(Array<EffectStepEntry> entries) {
        this.entries = new Array<>(entries);
    }

    public Array<EffectStep> stepsFor(ModelProperties modelProperties, Array<TargetType> targetTypes) {
        Array<EffectStep> filtered = new Array<>();

        for (int i = 0; i < entries.size; i++) {
            EffectStepEntry entry = entries.get(i);

            if (entry instanceof EffectStepEntry.Battle battle) {
                filtered.add(battle.stepSupplier().get(modelProperties));
                continue;
            }

            EffectStepEntry.Target target = (EffectStepEntry.Target) entry;
            if (targetTypes.contains(target.targetType(), true)) {
                filtered.add(target.stepSupplier().get(modelProperties));
            }
        }

        return filtered;
    }
}
