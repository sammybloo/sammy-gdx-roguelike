package io.bloogames.deckbuilder.effect;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.step.BattleStep;
import io.bloogames.deckbuilder.effect.step.TargetStep;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public final class EffectBuilder {
    private final Array<EffectStepEntry> entries = new Array<>();

    public EffectBuilder addBattleStep(BattleStep step) {
        entries.add(new EffectStepEntry.Battle(step));
        return this;
    }

    public <T extends Target> EffectBuilder addTargetStep(TargetType targetType, TargetStep<T> step) {
        entries.add(new EffectStepEntry.Target(targetType, step));
        return this;
    }

    public Effect build() {
        return new Effect(entries);
    }
}
