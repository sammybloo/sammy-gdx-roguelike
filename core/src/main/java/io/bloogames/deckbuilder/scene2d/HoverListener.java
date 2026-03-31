package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

public class HoverListener extends InputListener {

    boolean isHovered;
    private float delay;
    Timer.Task hoverTask;

    public HoverListener(float delay) {
        this.delay = delay;
    }

    public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
    }

    public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor) {
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        if (pointer != -1) return;

        hoverTask = Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                isHovered = true;
                onHoverStart(event, x, y, pointer, fromActor);
            }
        }, delay);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        if (pointer != -1) return;

        super.exit(event, x, y, pointer, toActor);
        if (isHovered) {
            onHoverEnd(event, x, y, pointer, toActor);
            isHovered = false;
        }
        if (hoverTask != null) {
            hoverTask.cancel();
            hoverTask = null;
        }
    }
}
