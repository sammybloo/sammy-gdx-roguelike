package io.bloogames.deckbuilder.model.damage;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public interface DamagePreventer {
    void onPrevent(TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    Priority priority();

    enum Priority {
        ONE_USE(100),
        ENDURING(1000);

        private final int speed;

        Priority(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }
    }
}
