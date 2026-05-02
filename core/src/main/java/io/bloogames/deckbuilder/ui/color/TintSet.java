package io.bloogames.deckbuilder.ui.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.github.tommyettinger.colorful.FloatColors;

public class TintSet {
    private final Array<Tint> tints = new Array<>();
    private final Color color = new Color();
    private Tintable parent;

    public void addTint(Tint tint) {
        if (!tints.contains(tint, true)) {
            tints.add(tint);
            tint.setParent(this);
        }
    }

    public void removeTint(Tint tint) {
        tints.removeValue(tint, true);
        tint.setParent(null);
        update();
    }

    public void removeTint(String id) {
        Tint target = null;

        for (Tint tint : tints) {
            if (id.equals(tint.getId())) {
                target = tint;
                break;
            }
        }

        if (target != null) {
            tints.removeValue(target, true);
            target.setParent(null);
            update();
        }
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
        if (parentColor.equals(Color.GRAY)) {
            return getColor();
        }
        Color.abgr8888ToColor(color, FloatColors.mix(getColor().toFloatBits(), parentColor.toFloatBits()));

        return color;
    }

    public void update() {
        if (parent != null) {
            parent.refreshColour();
        }
    }

    public void setParent(Tintable parent) {
        this.parent = parent;
    }
}
