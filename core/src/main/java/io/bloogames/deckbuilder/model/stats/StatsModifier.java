package io.bloogames.deckbuilder.model.stats;

import io.bloogames.deckbuilder.text.Describable;

public abstract class StatsModifier implements Describable {

    public abstract void calculate(Stats stats, StatChanges currentChanges);

    public abstract Priority priority();

    public enum Priority {
        MULTIPLY(99),
        DIVIDE(100),
        ADD_AND_SUBTRACT(500);

        private final int speed;

        Priority(int speed) {
            this.speed = speed;
        }

        public int getSpeed() {
            return speed;
        }
    }
}
