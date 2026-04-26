package io.bloogames.deckbuilder.damage;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public interface DamageModifier {

    boolean applies(SourceContext<?> sourceContext, TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    int apply(Damage damage, int currentAmount);

    int priority();

    public enum Priority {
        MULTIPLY(999),
        DIVIDE(999),
        ADD(500),
        SUBTRACT(500);

        public int speed;

        Priority(int speed) {
            this.speed = speed;
        }
    }
}
