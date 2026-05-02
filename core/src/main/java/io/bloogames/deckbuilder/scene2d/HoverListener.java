package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

public abstract class HoverListener extends ClickListener {

    private final float hoverDelay;
    private final float unhoverDelay;
    boolean isHovered;
    Timer.Task hoverTask;
    Timer.Task unhoverTask;

    public HoverListener(float hoverDelay, float unhoverDelay) {
        this.hoverDelay = hoverDelay;
        this.unhoverDelay = unhoverDelay;
    }

    public abstract void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor);

    public abstract void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor);

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        if (pointer != -1) return;
        if (y < 0) return;

        if (hoverTask == null) {
            hoverTask = Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    isHovered = true;
                    onHoverStart(event, x, y, pointer, fromActor);
                }
            }, hoverDelay);
        }

        if (unhoverTask != null) {
            unhoverTask.cancel();
            unhoverTask = null;
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);

        if (pointer != -1) return;

        if (unhoverTask == null) {
            unhoverTask = Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    isHovered = false;
                    onHoverEnd(event, x, y, pointer, toActor);
                }
            }, unhoverDelay);
        }

        if (hoverTask != null) {
            hoverTask.cancel();
            hoverTask = null;
        }
    }
}
