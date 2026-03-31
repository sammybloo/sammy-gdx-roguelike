package io.bloogames.deckbuilder.card;

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
    private float overlap = 0.3f;
    private float lift = 80f;
    private float maxRotation = 16f;
    private float normalScale = 0.5f;
    private float selectedScale = 0.8f;
    private float selectedLift = 150f;
    private float duration = 0.5f;

    private int selectedIndex = -1;

    private Array<Actor> fannables = new Array<>();

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        fan();
    }

    public void clearSelected() {
        this.selectedIndex = -1;
        fan();
    }

    private FannableLayout getFannableLayout(int index) {
        if (fannables.size == 0) return new FannableLayout(0f, 0f, 0f, normalScale);

        float handWidth = getWidth();
        float cardWidth = fannables.first().getWidth() * normalScale;
        float selectedCardAdjustment = (fannables.first().getWidth() * selectedScale * (1 - overlap)) / 2;

        float spacing = cardWidth * (1f - overlap);
        float totalWidth = spacing * (fannables.size) + cardWidth;
        float startX = (handWidth - totalWidth) / 2f;

        // centered is a value between -1 and 1 for each fannable
        float t = fannables.size == 1 ? 0.5f : index / (float) (fannables.size - 1);
        float centered = t * 2f - 1f;

        float x = startX + index * spacing;
        float y = lift * (1f - centered * centered);
        float rotation = -maxRotation * centered;
        float scale = normalScale;

        if (index == selectedIndex) {
            rotation = 0f;
            scale = selectedScale;
            y = selectedLift;
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
            child.addAction(Actions.scaleTo(layout.scale, layout.scale, duration));
            child.addAction(Actions.moveTo(layout.x, layout.y, duration));
            child.addAction(Actions.rotateTo(layout.rotation, duration));
        }

        if (selectedIndex >= 0 && selectedIndex < fannables.size) {
            fannables.get(selectedIndex).setZIndex(fannables.size + 1);
        }
    }

    @Override
    public Actor removeActorAt(int index, boolean unfocus) {
        fannables.removeIndex(index);
        return super.removeActorAt(index, unfocus);
    }

    @Override
    public boolean removeActor(Actor actor, boolean unfocus) {
        fannables.removeValue(actor, true);
        return super.removeActor(actor, unfocus);
    }

    @Override
    public boolean removeActor(Actor actor) {
        fannables.removeValue(actor, true);
        return super.removeActor(actor);
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
}
