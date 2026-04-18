package io.bloogames.deckbuilder.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public enum ViewUtils {
    ;

    public static void unmoor(Actor actor) {
        Stage stage = actor.getStage();

        Vector2 localPosition = new Vector2(actor.getWidth() * 0.5f, actor.getHeight() * 0.5f);
        Vector2 stagePosition = actor.localToStageCoordinates(localPosition);
        stage.addActor(actor);

        actor.setPosition(
            stagePosition.x - actor.getWidth() * 0.5f,
            stagePosition.y - actor.getHeight() * 0.5f
        );
    }
}
