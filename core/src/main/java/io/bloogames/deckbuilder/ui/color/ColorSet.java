package io.bloogames.deckbuilder.ui.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.colorful.FloatColors;

public class ColorSet {
    private Array<Tint> tints = new Array<>();
    private Color color = new Color();

    public void addTint(Tint tint) {
        if (!tints.contains(tint, true)) {
            tints.add(tint);
        }
    }

    public void removeTint(Tint tint) {
        tints.removeValue(tint, true);
    }

    public boolean hasTint() {
        return tints.size > 0;
    }

    public Color getColor() {
        if (tints.isEmpty()) {
            return Color.GRAY;
        }
        float[] floatColors = new float[tints.size];
        for (int i = 0; i < floatColors.length; i++) {
            floatColors[i] = tints.get(i).getColorFloatBits();
        }
        Color.abgr8888ToColor(color, FloatColors.mix(floatColors));
        return color;
    }

    public Color getColor(Color parentColor) {
        Color.abgr8888ToColor(color, FloatColors.mix(getColor().toFloatBits(), parentColor.toFloatBits()));
        return color;
    }

}
