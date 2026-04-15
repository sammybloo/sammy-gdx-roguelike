package io.bloogames.deckbuilder.ui.target;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface Targetable {
    TargetingVisualState targetingVisualState();

    void applyHighlight();

    default void setTargetState(TargetState state) {
        targetingVisualState().setTargetState(state);
        applyHighlight();
    }

    default void setHovered(boolean hovered) {
        targetingVisualState().setHoverState(
            hovered ? HoverState.HOVERED : HoverState.NOT_HOVERED
        );
        applyHighlight();
    }

    default void clearTargeting() {
        setTargetState(TargetState.NOT_TARGETED);
        setHovered(false);
    }

    Actor actor();
}
