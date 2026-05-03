package io.bloogames.deckbuilder.ui.scene2d;

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
    private final FanSettings settings;
    protected float fannableWidth;
    protected float fannableHeight;
    private Actor selectedActor = null;

    private Array<Actor> fannables = new Array<>();

    public FannedGroup(FanSettings fanSettings) {
        this.settings = fanSettings;
    }

    public void setFannableSize(float width, float height) {
        fannableWidth = width;
        fannableHeight = height;
    }

    public int getSelectedIndex() {
        return fannables.indexOf(selectedActor, true);
    }

    public void setSelectedActor(Actor actor) {
        selectedActor = actor;
        fan();
    }

    public void unselectActor(Actor actor) {
        if (selectedActor == actor) {
            clearSelected();
        }
    }

    public void clearSelected() {
        selectedActor = null;
        fan();
    }

    private FannableLayout getFannableLayout(int index) {
        if (fannables.size == 0) return new FannableLayout(0f, 0f, 0f, 1f);

        float handWidth = getWidth();
        float selectedCardAdjustment = (fannableWidth * settings.selectedScale * (1 - settings.overlap)) / 2;

        float spacing = fannableWidth * (1f - settings.overlap);
        float totalWidth = spacing * (fannables.size) + (fannableWidth * settings.overlap);
        float startX = (handWidth - totalWidth) / 2f;

        // centered is a value between -1 and 1 for each fannable
        float t = fannables.size == 1 ? 0.5f : index / (float) (fannables.size - 1);
        float centered = t * 2f - 1f;

        float x = startX + index * spacing;
        float y = (fannableHeight * settings.lift) * (1f - centered * centered);
        float rotation = -settings.maxRotation * centered;
        float scale = 1;

        int selectedIndex = getSelectedIndex();
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
            child.setZIndex(i);
            child.setOrigin(fannableWidth * 0.5f, fannableHeight * 0.5f);
            child.addAction(Actions.sizeTo(fannableWidth, fannableHeight, settings.duration));
            child.addAction(Actions.scaleTo(layout.scale, layout.scale, settings.duration));
            child.addAction(Actions.moveTo(layout.x, layout.y, settings.duration));
            child.addAction(Actions.rotateTo(layout.rotation, settings.duration));
        }

        if (selectedActor != null) {
            selectedActor.toFront();
        }
    }

    public void setFannables(Array<Actor> actors) {
        fannables = actors;
        fan();
    }

    public void addFannable(Actor actor) {
        fannables.add(actor);
    }

    public void removeFannable(Actor actor) {
        fannables.removeValue(actor, true);
    }

    private record FannableLayout(float x, float y, float rotation, float scale) {
    }

    public record FanSettings(float overlap, float lift, float maxRotation,
                              float selectedScale, float selectedLift, float selectedRotation,
                              float duration) {
    }
}
