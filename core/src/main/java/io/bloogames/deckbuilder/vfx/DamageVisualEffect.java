package io.bloogames.deckbuilder.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.scene2d.ResizableContainer;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.color.Tint;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static io.bloogames.deckbuilder.manager.CustomActions.tint;

public class DamageVisualEffect implements VisualEffect {

    private Action flashAction;
    private Action damageBubbleAction;
    private Action shakeAction;
    private Actor actor;
    private ResizableContainer damageLabel;

    public DamageVisualEffect(ResizableGroup target, View targetView, int amount) {
        this.actor = target;
        Tint tint = new Tint();
        target.addTint(tint);

        this.flashAction = sequence(tint(tint, Color.RED.toFloatBits(), 0.2f),
            run(targetView::sync),
            tint(tint, Color.GRAY.toFloatBits(), 0.2f),
            run(() -> target.removeTint(tint)));

        this.shakeAction = sequence(
            moveBy(10, 0, 0.03f),
            moveBy(-20, 0, 0.06f),
            moveBy(20, 0, 0.06f),
            moveBy(-10, 0, 0.03f)
        );
        this.damageBubbleAction = sequence(
            alpha(0),
            scaleTo(0, 0),
            parallel(
                fadeIn(0.1f),
                scaleTo(1.75f, 1.75f, 0.2f)
            ),
            delay(0.1f),
            scaleTo(1, 1, 0.1f),
            parallel(
                moveBy(0, 100f, 0.5f),
                sequence(
                    delay(0.2f),
                    fadeOut(0.3f)
                )
            ),
            removeActor());
        var label = new Label(amount + "", new Label.LabelStyle(FontManager.INSTANCE.getDamagePopupFont(), Color.GRAY));
        this.damageLabel = new ResizableContainer(label,
            new ResizableSettings(200f, 200f, Align.center));
        label.setAlignment(Align.center, Align.center);
        damageLabel.setTouchable(Touchable.disabled);
        damageLabel.setColor(0.5f, 0.5f, 0.5f, 1f);
    }

    @Override
    public void play() {
        actor.addAction(flashAction);
        actor.addAction(shakeAction);
        actor.getStage().addActor(damageLabel);

        Vector2 localPosition = new Vector2();
        Vector2 stagePosition = actor.localToStageCoordinates(localPosition);
        damageLabel.setBounds(stagePosition.x, stagePosition.y, actor.getWidth(), actor.getHeight());
        damageLabel.setOrigin(Align.center);
        damageLabel.addAction(damageBubbleAction);
    }

    @Override
    public boolean isReady() {
        return flashAction.getActor() == null && shakeAction.getActor() == null;
    }
}
