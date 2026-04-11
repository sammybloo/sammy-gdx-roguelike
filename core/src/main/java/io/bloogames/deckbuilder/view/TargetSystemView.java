package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.Crosshair;
import io.bloogames.deckbuilder.ui.HighlightState;
import io.bloogames.deckbuilder.ui.Highlightable;

public class TargetSystemView extends ResizableGroup implements InputProcessor {

    public static final float WIDTH = 1920f;
    public static final float HEIGHT = 1080f;

    private Crosshair crosshair;
    private Group cardSpot;
    private CardView card;
    private Vector2 mousePosition = new Vector2();
    private Highlightable target;

    public TargetSystemView() {
        super(WIDTH, HEIGHT);
        setTouchable(Touchable.disabled);
        cardSpot = new Group();
        cardSpot.setBounds(10, 200, WIDTH, HEIGHT);
        crosshair = new Crosshair();
        crosshair.setVisible(false);
        register(cardSpot, new ResizeableSettings(320, 540));
        addActor(crosshair);
    }

    public void attemptTargeting(CardView card) {
        this.card = card;
        card.clearActions();
        cardSpot.addActor(card);
        card.setRotation(cardSpot.getRotation());
        card.setBounds(cardSpot.getX(), cardSpot.getY(), cardSpot.getWidth(), cardSpot.getHeight());
        card.setScale(cardSpot.getScaleX(), cardSpot.getScaleY());
        enableCrosshair();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getStage() == null) return;

        mousePosition.set(Gdx.input.getX(), Gdx.input.getY());
        Vector2 stageCoordinates = getStage().getViewport().unproject(mousePosition);
        Actor hoveredActor = getStage().hit(stageCoordinates.x, stageCoordinates.y, true);

        if (hoveredActor instanceof Highlightable hoveredHighlightable) {
            updateTarget(hoveredHighlightable);
        } else {
            clearTargetHighlight();
        }
    }

    private void updateTarget(Highlightable newTarget) {
        if (target != null && target != newTarget) {
            target.clearHighlight();
        }

        newTarget.setHighlightState(HighlightState.VALID);
        target = newTarget;
    }

    private void clearTargetHighlight() {
        if (target != null) {
            target.clearHighlight();
            target = null;
        }
    }

    public boolean isTargeting() {
        return card != null;
    }

    public CardView getCard() {
        return card;
    }

    public void cancelTargeting() {
        this.card = null;
        disableCrosshair();
    }

    public void enableCrosshair() {
        crosshair.setVisible(true);
    }

    public void disableCrosshair() {
        crosshair.setVisible(false);
    }


    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (card == null || button != Input.Buttons.RIGHT) return false;
        // TODO replace functionality
//        CommandManager.INSTANCE.processImmediately(new CancelTargetCommand());
        return true;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
