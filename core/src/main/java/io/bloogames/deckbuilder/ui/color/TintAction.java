package io.bloogames.deckbuilder.ui.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.github.tommyettinger.colorful.FloatColors;

public class TintAction extends TemporalAction {
    private float start, end;
    private Tint tint;

    protected void begin() {
        start = tint.getColorFloatBits();
    }

    protected void update(float percent) {
        if (percent == 0)
            tint.setColorFloatBits(start);
        else if (percent == 1)
            tint.setColorFloatBits(end);
        else {
            tint.setColorFloatBits(FloatColors.lerpFloatColors(start, end, percent));
        }
    }

    public void reset() {
        super.reset();
    }

    public Tint getTint() {
        return tint;
    }

    public void setTint(Tint tint) {
        this.tint = tint;
    }

    public float getEnd() {
        return end;
    }

    public void setEndColor(Color color) {
        end = color.toFloatBits();
    }

    public void setEndColor(float colorFloatBits) {
        this.end = colorFloatBits;
    }
}
