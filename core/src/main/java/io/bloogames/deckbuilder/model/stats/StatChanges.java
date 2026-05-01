package io.bloogames.deckbuilder.model.stats;

public class StatChanges {
    private int power = 0;
    private int health = 0;

    public StatChanges() {
    }

    public StatChanges(StatChanges statChanges) {
        health = statChanges.getHealth();
        power = statChanges.getPower();
    }

    public StatChanges(int power, int health) {
        this.health = health;
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealthBy(int amount) {
        this.health += amount;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void changePowerBy(int amount) {
        this.power += amount;
    }

    public void changeBy(StatChanges changes) {
        changePowerBy(changes.getPower());
        changeHealthBy(changes.getHealth());
    }
}
