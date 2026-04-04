package io.bloogames.deckbuilder.scene2d;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

/*
 *  Makes a group of objects fan out like a hand of cards.
 *  Optionally, can have a selected card that sits in front of the rest.
 *  Use fan() to redistribute the hand :)
 */
public class FannedGroup extends Group {
    private FanSettings settings;
    protected float fannableWidth;
    protected float fannableHeight;

    private int selectedIndex = -1;

    private Array<Actor> fannables = new Array<>();

    public FannedGroup(FanSettings fanSettings) {
        this.settings = fanSettings;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        fan();
    }

    public void setFannableSize(float width, float height) {
        fannableWidth = width;
        fannableHeight = height;
    }

    public void setSelectedActor(Actor actor) {
        setSelectedIndex(fannables.indexOf(actor, true));
    }

    public void unselectActor(Actor actor) {
        if (selectedIndex == fannables.indexOf(actor, true)) {
            clearSelected();
        }
    }

    public void clearSelected() {
        selectedIndex = -1;
        fan();
    }

    private FannableLayout getFannableLayout(int index) {
        if (fannables.size == 0) return new FannableLayout(0f, 0f, 0f, 1f);

        float handWidth = getWidth();
        float selectedCardAdjustment = (fannableWidth * settings.selectedScale * (1 - settings.overlap)) / 2;

        float spacing = fannableWidth * (1f - settings.overlap);
        float totalWidth = spacing * (fannables.size);
        float startX = (handWidth - totalWidth) / 2f;

        // centered is a value between -1 and 1 for each fannable
        float t = fannables.size == 1 ? 0.5f : index / (float) (fannables.size - 1);
        float centered = t * 2f - 1f;

        float x = startX + index * spacing;
        float y = (fannableHeight * settings.lift) * (1f - centered * centered);
        float rotation = -settings.maxRotation * centered;
        float scale = 1;

        if (index == selectedIndex) {
            rotation = settings.selectedRotation;
            scale = settings.selectedScale;
            y = fannableHeight * settings.selectedLift;
        } else if (selectedIndex != -1) {
            if (index < selectedIndex) {
                x -= selectedCardAdjustment;
            } else {
                x += selectedCardAdjustment;
            }
        }
        return new FannableLayout(x, y, rotation, scale);
    }

    public void fan() {
        for (int i = 0; i < fannables.size; i++) {
            Actor child = fannables.get(i);
            FannableLayout layout = getFannableLayout(i);
            //child.clearActions();
            child.setZIndex(i);
            child.setOrigin(fannableWidth * 0.5f, fannableHeight * 0.5f);
            child.addAction(Actions.sizeTo(fannableWidth, fannableHeight, settings.duration));
            child.addAction(Actions.scaleTo(layout.scale, layout.scale, settings.duration));
            child.addAction(Actions.moveTo(layout.x, layout.y, settings.duration));
            child.addAction(Actions.rotateTo(layout.rotation, settings.duration));
        }

        if (selectedIndex >= 0 && selectedIndex < fannables.size) {
            fannables.get(selectedIndex).toFront();
        }
    }

    @Override
    public Actor removeActorAt(int index, boolean unfocus) {
        fannables.removeIndex(index);
        return super.removeActorAt(index, unfocus);
    }

    @Override
    public void addActorAfter(Actor actorAfter, Actor actor) {
        fannables.insert(fannables.indexOf(actorAfter, true) + 1, actor);
        super.addActorAfter(actorAfter, actor);
    }

    @Override
    public void addActorBefore(Actor actorBefore, Actor actor) {
        fannables.insert(fannables.indexOf(actorBefore, true), actor);
        super.addActorBefore(actorBefore, actor);
    }

    @Override
    public void addActorAt(int index, Actor actor) {
        if (index > fannables.size) index = fannables.size;
        fannables.insert(index, actor);
        super.addActorAt(index, actor);
    }

    @Override
    public void addActor(Actor actor) {
        fannables.add(actor);
        super.addActor(actor);
    }

    @Override
    public boolean swapActor(int first, int second) {
        var aux = fannables.get(first);
        fannables.set(first, fannables.get(second));
        fannables.set(second, aux);
        return super.swapActor(first, second);
    }

    @Override
    public boolean swapActor(Actor first, Actor second) {
        swapActor(fannables.indexOf(first, true), fannables.indexOf(second, true));
        return super.swapActor(first, second);
    }

    private record FannableLayout(float x, float y, float rotation, float scale) {
    }

    public record FanSettings(float overlap, float lift, float maxRotation,
                              float selectedScale, float selectedLift, float selectedRotation,
                              float duration) {
    }
}
