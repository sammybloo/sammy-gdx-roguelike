package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.graphics.Color;

public class ParticipantModel {
    private Color colour;
    private int maxHealth;

    public ParticipantModel(Color colour, int maxHealth) {
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
