package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.graphics.Color;

public class BaseLeader {
    private Color colour;
    private String id;
    private int maxHealth;

    public BaseLeader(String id, int maxHealth) {
        this.colour = colour;
        this.id = id;
        this.maxHealth = maxHealth;
    }

    public String getId() {
        return id;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
