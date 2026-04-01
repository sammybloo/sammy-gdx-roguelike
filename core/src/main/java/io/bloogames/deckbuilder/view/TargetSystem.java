package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Group;
import io.bloogames.deckbuilder.command.CancelTargetCommand;
import io.bloogames.deckbuilder.manager.CommandManager;
import io.bloogames.deckbuilder.ui.Crosshair;

public class TargetSystem extends Group implements InputProcessor {

    public static final float WIDTH = 360f;
    public static final float HEIGHT = 540f;

    private Crosshair crosshair;
    private Card card;

    public TargetSystem() {
        crosshair = new Crosshair();
        crosshair.setVisible(false);
        addActor(crosshair);
    }

    public void attemptTargeting(Card card) {
        this.card = card;
        card.clearActions();
        card.setRotation(getRotation());
        card.setBounds(getX(), getY(), getWidth(), getHeight());
        card.setScale(getScaleX(), getScaleY());
        enableCrosshair();
        addActor(card);
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
        CommandManager.INSTANCE.processImmediately(new CancelTargetCommand(card));
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
