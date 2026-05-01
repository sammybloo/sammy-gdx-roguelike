package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.bloogames.deckbuilder.vfx.VFXUtils;
import io.bloogames.deckbuilder.vfx.VisualEffect;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class DieEffect implements VisualEffect {
    Action action;
    Actor actor;

    public DieEffect(Actor actor) {
        this.actor = actor;
        this.action = sequence(
            parallel(
                rotateBy(60f, 0.5f),
                scaleTo(0f, 0f, 0.5f),
                fadeOut(0.5f)
            ), removeActor());
    }

    @Override
    public void play() {
        VFXUtils.unmoor(actor);
        actor.addAction(action);
    }

    @Override
    public boolean isReady() {
        return action.getActor() == null;
    }
}
