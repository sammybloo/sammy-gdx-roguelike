package io.bloogames.deckbuilder.ui.target;

import com.badlogic.gdx.graphics.Color;

public final class TargetingVisualState {
    private TargetState targetState = TargetState.NOT_TARGETED;
    private HoverState hoverState = HoverState.NOT_HOVERED;

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

    public Color getColour() {
        if (!isTargeted()) {
            return new Color(1f, 1f, 1f, 1f);
        }
        if (targetState == TargetState.VALID) {
            if (hoverState == HoverState.HOVERED) {
                return new Color(1f, 1f, 0.5f, 1f);
            }
            return new Color(0.8f, 1f, 0.8f, 1f);
        }
        return new Color(0.8f, 0.8f, 0.8f, 1f);
    }
}
