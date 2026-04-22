package io.bloogames.deckbuilder.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.github.tommyettinger.colorful.ipt.ColorfulSprite;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.scene2d.ResizableContainer;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class DamageVisualEffect implements VisualEffect {

    private Action action;
    private Actor actor;
    private ResizableContainer damageLabel;

    public DamageVisualEffect(Actor actor, int amount) {
        this.actor = actor;
        this.action = sequence(color(Color.RED, 0.2f), color(actor.getColor(), 0.2f));
        this.damageLabel = new ResizableContainer(new Label(amount + "",
            new Label.LabelStyle(FontManager.INSTANCE.getDamagePopupFont(), null)),
            new ResizableSettings(200f, 200f, Align.center));
        damageLabel.setTouchable(Touchable.disabled);
        damageLabel.setOrigin(Align.center);
        ((Label) (damageLabel.getActor())).setAlignment(Align.center, Align.center);
        ColorfulSprite s;
    }

    @Override
    public void play() {
        actor.addAction(action);
        actor.getStage().addActor(damageLabel);

        Vector2 localPosition = new Vector2(actor.getWidth() * 0.5f, actor.getHeight() * 0.5f);
        Vector2 stagePosition = actor.localToStageCoordinates(localPosition);
        damageLabel.setPosition(stagePosition.x - damageLabel.getActor().getWidth() / 2, stagePosition.y - damageLabel.getActor().getHeight() / 2);
        damageLabel.addAction(
            sequence(
                alpha(0),
                delay(0.1f),
                scaleTo(0, 0),
                parallel(
                    fadeIn(0.1f),
                    scaleTo(1.2f, 1.2f, 0.1f)
                ),
                scaleTo(1, 1, 0.1f),
                delay(0.1f),
                parallel(
                    fadeOut(0.5f),
                    moveBy(0, 50f, 0.5f)
                ),
                removeActor())
        );
    }

    @Override
    public boolean isReady() {
        return action.getActor() != null;
    }
}
