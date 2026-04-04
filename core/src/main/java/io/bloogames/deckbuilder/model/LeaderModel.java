package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.graphics.Color;
import io.bloogames.deckbuilder.data.BaseLeader;

public class LeaderModel {
    private BaseLeader base;
    private int damage;

    public LeaderModel(BaseLeader base) {
        this.base = base;
    }

    public Color getColour() {
        return base.getColour();
    }

    public int getMaxHealth() {
        return base.getMaxHealth();
    }

    public int getDamage() {
        return damage;
    }
}
