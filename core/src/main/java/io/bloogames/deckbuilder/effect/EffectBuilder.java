package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.step.BattleStep;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public final class EffectBuilder {
    private final Array<BattleStep> battleSteps = new Array<>();
    private final ObjectMap<TargetType, Array<TargetStep<? extends Target>>> targetStepsByType = new ObjectMap<>();

    public EffectBuilder addBattleStep(BattleStep step) {
        battleSteps.add(step);
        return this;
    }

    public <T extends Target> EffectBuilder addTargetStep(TargetType type, TargetStep<T> step) {
        Array<TargetStep<? extends Target>> steps = targetStepsByType.get(type);
        if (steps == null) {
            steps = new Array<>();
            targetStepsByType.put(type, steps);
        }
        steps.add(step);
        return this;
    }

    public Effect build() {
        return new Effect(battleSteps, targetStepsByType);
    }
}
