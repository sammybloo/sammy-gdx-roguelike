package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.graphics.Color;

public class BaseLeader {
    private Color colour;
    private int maxHealth;

    public BaseLeader(Color colour, int maxHealth) {
        this.colour = colour;
        this.maxHealth = maxHealth;
    }

    public Color getColour() {
        return colour;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
