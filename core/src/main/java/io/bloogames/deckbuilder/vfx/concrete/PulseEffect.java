package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.bloogames.deckbuilder.vfx.VisualEffect;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class PulseEffect implements VisualEffect {
    public Action pulseAction;
    public Actor actor;

    public PulseEffect(Actor actor) {
        this.actor = actor;
        pulseAction = sequence(scaleTo(2f, 2f, 0.1f), scaleTo(1f, 1f, 0.2f));

    }

    @Override
    public void play() {
        actor.addAction(pulseAction);
    }

    @Override
    public boolean isReady() {
        return pulseAction.getActor() == null;
    }
}
