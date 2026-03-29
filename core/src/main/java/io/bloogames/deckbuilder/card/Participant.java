package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.graphics.Color;

public class Participant {
    private Color colour;
    private int maxHealth;
    private int damage;

    public Participant(Color colour, int maxHealth) {
        this.colour = colour;
        this.maxHealth = maxHealth;
    }

    public Color getColour() {
        return colour;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }
}
