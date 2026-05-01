package io.bloogames.deckbuilder.model.stats;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.damage.Damage;

public interface StatsModifier {

    float calculate(Stats stats, StatChanges currentChanges);

    Priority priority();

    enum Priority {
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
