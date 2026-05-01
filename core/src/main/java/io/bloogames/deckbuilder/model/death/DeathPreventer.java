package io.bloogames.deckbuilder.model.death;

import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;

public interface DeathPreventer {
    void onPrevent(BattlerTarget target, Death death);

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
