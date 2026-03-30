package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.graphics.Color;

public class Participant {
    private ParticipantModel model;
    private int damage;

    public Participant(ParticipantModel model) {
        this.model = model;
    }

    public Color getColour() {
        return model.getColour();
    }

    public int getMaxHealth() {
        return model.getMaxHealth();
    }

    public int getDamage() {
        return damage;
    }
}
