package io.bloogames.deckbuilder.model;

public interface Damageable {
    int damage(int amount);

    int heal(int amount);
}
