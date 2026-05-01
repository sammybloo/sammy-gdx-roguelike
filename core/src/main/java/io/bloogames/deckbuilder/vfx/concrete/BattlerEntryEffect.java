package io.bloogames.deckbuilder.vfx.concrete;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.bloogames.deckbuilder.vfx.VisualEffect;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class BattlerEntryEffect implements VisualEffect {

    Actor actor;
    Action action;

    public BattlerEntryEffect(Actor actor) {
        this.actor = actor;
        this.action = parallel(
            fadeIn(0.2f),
            sequence(
                scaleTo(1.2f, 1.2f),
                scaleTo(1, 1, 0.2f)));
    }

    @Override
    public void play() {
        actor.addAction(action);
    }

    @Override
    public boolean isReady() {
        return action.getActor() == null;
    }
}
