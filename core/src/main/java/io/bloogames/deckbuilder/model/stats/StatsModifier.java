package io.bloogames.deckbuilder.model.stats;

public interface StatsModifier {

    void calculate(Stats stats, StatChanges currentChanges);

    Priority priority();

    enum Priority {
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
