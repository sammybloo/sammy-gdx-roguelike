package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import io.bloogames.deckbuilder.ui.ViewUtils;
import io.bloogames.deckbuilder.vfx.VisualEffect;

public class DisappearEffect implements VisualEffect {

    private Actor actor;

    public DisappearEffect(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void play() {
        actor.clearListeners();
        actor.clearActions();
        ViewUtils.unmoor(actor);

        actor.addAction(Actions.moveBy(0, 100f, 0.2f));
        actor.addAction(Actions.fadeOut(0.2f));
        actor.addAction(Actions.delay(0.2f, Actions.removeActor()));
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
