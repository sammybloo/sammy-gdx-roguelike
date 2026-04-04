package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import io.bloogames.deckbuilder.model.LeaderModel;

public class Leader {
    private LeaderModel model;

    public Leader(LeaderModel model) {
        this.model = model;
    }

    public Color getColour() {
        return model.getColour();
    }

    public int getMaxHealth() {
        return model.getMaxHealth();
    }

    public LeaderModel getModel() {
        return model;
    }
}
