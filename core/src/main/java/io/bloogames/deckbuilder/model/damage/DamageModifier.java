package io.bloogames.deckbuilder.model.damage;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public interface DamageModifier {

    float calculate(Damage damage, float currentAmount);

    //override for modifiers that actually need to do something when damage is modified
    default float apply(TargetContext<DamageableTarget> context, Damage damage, float currentAmount) {
        return calculate(damage, currentAmount);
    }

    Priority priority();

    enum Priority {
        SHIELD(10),
        MULTIPLY(99),
        DIVIDE(100),
        ADD(500),
        SUBTRACT(500);

        private final int speed;

        Priority(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }
    }
}
