package io.bloogames.deckbuilder.damage;

import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public interface DamagePreventer {
    boolean applies(TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    void onPrevent(TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    Priority priority();

    public enum Priority {
        ONE_USE(100),
        ENDURING(1000);

        private int speed;

        Priority(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }
    }
}
