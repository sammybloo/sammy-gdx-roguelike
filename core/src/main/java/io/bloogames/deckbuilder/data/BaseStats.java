package io.bloogames.deckbuilder.data;

public class BaseStats {
    private int health;
    private int power;

    public BaseStats(int health, int power) {
        this.health = health;
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public int getPower() {
        return power;
    }
}
