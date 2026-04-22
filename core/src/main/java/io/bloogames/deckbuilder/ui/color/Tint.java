package io.bloogames.deckbuilder.ui.color;

import com.badlogic.gdx.graphics.Color;

public class Tint {
        private float colorFloatBits;

    public Tint(float colorFloatBits) {
        this.colorFloatBits = colorFloatBits;
    }

    public float getColorFloatBits() {
        return colorFloatBits;
    }

    public void setColorFloatBits(float colorFloatBits) {
        this.colorFloatBits = colorFloatBits;
    }

    public void setColor(Color color) {
        this.colorFloatBits = color.toFloatBits();
    }
}
