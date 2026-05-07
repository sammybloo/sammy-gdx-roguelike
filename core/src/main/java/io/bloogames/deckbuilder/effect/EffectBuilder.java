package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.step.StepFactory;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public final class EffectBuilder {
    private final Array<EffectStepEntry> entries = new Array<>();

    public EffectBuilder addBattleStep(StepFactory stepFactory) {
        entries.add(new EffectStepEntry.Battle(stepFactory));
        return this;
    }

    public <T extends Target> EffectBuilder addTargetStep(TargetType targetType, StepFactory stepFactory) {
        entries.add(new EffectStepEntry.Target(targetType, stepFactory));
        return this;
    }

    public Effect build() {
        return new Effect(entries);
    }
}
