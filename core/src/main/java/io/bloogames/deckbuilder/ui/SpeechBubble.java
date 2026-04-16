package io.bloogames.deckbuilder.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Align;

public class SpeechBubble extends Actor {
    private final NinePatch background;
    private final Label child;
    private final float templateWidth;
    private final float templateHeight;

    private float padLeft = 24f;
    private float padRight = 24f;
    private float padTop = 24f;
    private float padBottom = 24f;

    public SpeechBubble(NinePatch background, Label child, float templateWidth, float templateHeight) {
        this.background = background;
        this.child = child;
        this.templateWidth = templateWidth;
        this.templateHeight = templateHeight;

        if (child != null) {
            child.setOrigin(Align.center);
        }
    }

    public void setPadding(float left, float right, float top, float bottom) {
        this.padLeft = left;
        this.padRight = right;
        this.padTop = top;
        this.padBottom = bottom;
        layout();
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        layout();
    }

    public void layout() {
        if (child == null) return;

        float contentWidth = Math.max(0f, getWidth() - padLeft - padRight);
        float contentHeight = Math.max(0f, getHeight() - padTop - padBottom);

        child.setBounds(padLeft, padBottom, contentWidth, contentHeight);
        child.layout();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float alpha = getColor().a * parentAlpha;

        Color oldBatchColor = batch.getColor();
        batch.setColor(getColor().r, getColor().g, getColor().b, oldBatchColor.a * alpha);

        background.draw(
            batch,
            getX(), getY(),
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY(),
            getRotation()
        );

        if (child != null) {
            child.draw(batch, child.getColor().a * alpha);
        }

        batch.setColor(oldBatchColor);
    }

    public Label getLabel() {
        return child;
    }
}
