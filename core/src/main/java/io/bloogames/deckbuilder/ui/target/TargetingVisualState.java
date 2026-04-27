package io.bloogames.deckbuilder.ui.target;

import com.badlogic.gdx.graphics.Color;
import io.bloogames.deckbuilder.ui.color.Tint;

public final class TargetingVisualState {
    private TargetState targetState = TargetState.NOT_TARGETED;
    private HoverState hoverState = HoverState.NOT_HOVERED;
    private final Tint currentTint = new Tint(Color.GRAY.toFloatBits());

    public TargetState getTargetState() {
        return targetState;
    }

    public void setTargetState(TargetState targetState) {
        this.targetState = targetState;
    }

    public HoverState getHoverState() {
        return hoverState;
    }

    public void setHoverState(HoverState hoverState) {
        this.hoverState = hoverState;
    }

    public boolean isTargeted() {
        return targetState != TargetState.NOT_TARGETED;
    }

    public boolean isHovered() {
        return hoverState == HoverState.HOVERED;
    }

    public Tint getTint() {
        return currentTint;
    }

    public void updateTint() {
        currentTint.setColor(getColor());
    }

    private Color getColor() {
        if (!isTargeted()) {
            return new Color(0.5f, 0.5f, 0.5f, 1f);
        }
        if (targetState == TargetState.VALID) {
            if (hoverState == HoverState.HOVERED) {
                return new Color(0.7f, 0.7f, 0.5f, 1f);
            }
            return new Color(0.65f, 0.65f, 0.5f, 1f);
        }
        return new Color(0.3f, 0.3f, 0.3f, 1f);
    }
}
