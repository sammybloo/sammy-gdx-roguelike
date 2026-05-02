package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import io.bloogames.deckbuilder.ui.color.Tint;
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.concrete.PulseEffect;

public class UpdatingLabel extends ResizableGroup {
    public static final float NEGATIVE_COLOUR = new Color(1f, 0f, 0f, 1).toFloatBits();
    public static final float POSITIVE_COLOUR = new Color(0f, 1f, 0f, 1).toFloatBits();
    public static final float NEUTRAL_COLOUR = Color.BLACK.toFloatBits();
    private final Label label;
    private final Tint tint = new Tint();
    private String text;

    public UpdatingLabel(float targetWidth, float targetHeight, String text, BitmapFont font) {
        super(targetWidth, targetHeight);
        this.label = new Label(text, new Label.LabelStyle(font, null));
        this.text = text;
        register(label, new ResizableSettings(targetWidth, targetHeight));
        addTint(tint);
        setTouchable(Touchable.disabled);
        setNeutral();
    }

    public void setPositive() {
        tint.setColorFloatBits(POSITIVE_COLOUR);
    }

    public void setNegative() {
        tint.setColorFloatBits(NEGATIVE_COLOUR);
    }

    public void setNeutral() {
        tint.setColorFloatBits(NEUTRAL_COLOUR);
    }

    public void setColourByComparison(float base, float current) {
        if (base == current) {
            setNeutral();
        } else if (base > current) {
            setNegative();
        } else {
            setPositive();
        }
    }

    public void setColourByComparisonInverted(float base, float current) {
        setColourByComparison(current, base);
    }

    public Label getLabel() {
        return label;
    }

    public void setText(String text) {
        label.setText(text);
        if (!this.text.equals(text)) {
            VFXManager.INSTANCE.addEffect(new PulseEffect(this));
        }
        this.text = text;
    }
}
