package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.step.BattleStep;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public final class Effect {
    private final Array<BattleStep> battleSteps;
    private final ObjectMap<TargetType, Array<TargetStep<? extends Target>>> targetStepsByType;

    public Effect(
        Array<BattleStep> battleSteps,
        ObjectMap<TargetType, Array<TargetStep<? extends Target>>> targetStepsByType
    ) {
        this.battleSteps = new Array<>(battleSteps);
        this.targetStepsByType = new ObjectMap<>();
        for (var entry : targetStepsByType.entries()) {
            this.targetStepsByType.put(entry.key, new Array<>(entry.value));
        }
    }

    public Array<BattleStep> battleSteps() {
        return new Array<>(battleSteps);
    }

    public Array<TargetStep<? extends Target>> targetSteps(TargetType type) {
        Array<TargetStep<? extends Target>> steps = targetStepsByType.get(type);
        return steps == null ? new Array<>() : new Array<>(steps);
    }

    public boolean hasTargetSteps(TargetType type) {
        Array<TargetStep<? extends Target>> steps = targetStepsByType.get(type);
        return steps != null && steps.size > 0;
    }
}
