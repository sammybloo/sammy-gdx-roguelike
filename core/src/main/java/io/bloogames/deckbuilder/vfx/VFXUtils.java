package io.bloogames.deckbuilder.vfx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public enum VFXUtils {
    ;

    public static void unmoor(Actor actor) {
        Group actorGroup = VFXManager.INSTANCE.getVfxActorGroup();

        if (actorGroup == null) {
            return;
        }

        Vector2 localPosition = new Vector2(actor.getWidth() * 0.5f, actor.getHeight() * 0.5f);
        Vector2 stagePosition = actor.localToActorCoordinates(actorGroup, localPosition);
        VFXManager.INSTANCE.addToActorGroup(actor);

        actor.setPosition(
            stagePosition.x - actor.getWidth() * 0.5f,
            stagePosition.y - actor.getHeight() * 0.5f
        );
    }
}
