package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

public class ResizableContainer extends Container<Actor> {
    private ResizableGroup.ResizeableSettings settings;

    public ResizableContainer(Actor actor, ResizableGroup.ResizeableSettings settings) {
        super(actor);
        actor.setPosition(0, 0);
        this.settings = settings;
    }

    public ResizableGroup.ResizeableSettings getSettings() {
        return settings;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        getActor().setSize(width, height);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        getActor().setSize(width, height);
    }
}
