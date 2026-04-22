package io.bloogames.deckbuilder.ui.color;

import com.badlogic.gdx.graphics.Color;

public class Tint {
    private TintSet parent;
    private float colorFloatBits;

    public Tint() {
        this.colorFloatBits = Color.GRAY.toFloatBits();
    }

    public Tint(float colorFloatBits) {
        this.colorFloatBits = colorFloatBits;
    }

    public float getColorFloatBits() {
        return colorFloatBits;
    }

    public void setColorFloatBits(float colorFloatBits) {
        this.colorFloatBits = colorFloatBits;
        if (parent != null) {
            parent.update();
        }
    }

    public void setColor(Color color) {
        setColorFloatBits(color.toFloatBits());
    }

    public void setParent(TintSet parent) {
        this.parent = parent;
    }
}
