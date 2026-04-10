package io.bloogames.deckbuilder.data;

public class BaseLeader {
    private String id;
    private int maxHealth;
    private int maxMana;

    public BaseLeader(String id, int maxHealth, int maxMana) {
        this.id = id;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
    }

    public String getId() {
        return id;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }
}
