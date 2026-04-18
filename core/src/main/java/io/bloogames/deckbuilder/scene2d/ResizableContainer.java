package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Align;

public class ResizableContainer extends Group {
    private final Actor actor;
    private ResizableSettings settings;

    public ResizableContainer(Actor actor, ResizableSettings settings) {
        this.settings = settings;
        this.actor = actor;
        setTouchable(Touchable.childrenOnly);
        actor.setPosition(0, 0);
        actor.setOrigin(Align.center);
        addActor(actor);
    }

    public ResizableSettings getSettings() {
        return settings;
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        actor.setSize(getWidth(), getHeight());
        actor.setOrigin(Align.center);
        if (actor instanceof Widget widget) {
            widget.invalidateHierarchy();
        }
    }
}
