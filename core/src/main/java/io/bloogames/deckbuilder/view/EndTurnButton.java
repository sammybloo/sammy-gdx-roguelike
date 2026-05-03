package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public class EndTurnButton extends ResizableGroup implements Targetable {

    TargetingVisualState targetingVisualState = new TargetingVisualState();


    public EndTurnButton() {
        super(200, 100);
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public void applyHighlight() {

    }

    @Override
    public Actor actor() {
        return this;
    }
}
