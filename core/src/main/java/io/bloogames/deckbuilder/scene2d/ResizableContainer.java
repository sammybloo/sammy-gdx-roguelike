package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Align;

public class ResizableContainer extends Container<Actor> {
    private ResizableGroup.ResizeableSettings settings;

    public ResizableContainer(Actor actor, ResizableGroup.ResizeableSettings settings) {
        super(actor);
        actor.setPosition(0, 0);
        this.settings = settings;
        getActor().setOrigin(Align.center);
    }

    public ResizableGroup.ResizeableSettings getSettings() {
        return settings;
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        getActor().setSize(getWidth(), getHeight());
        getActor().setOrigin(Align.center);
        invalidateHierarchy();
        layout();
    }
}
