package io.bloogames.deckbuilder.vfx.concrete;

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
import io.bloogames.deckbuilder.vfx.VFXManager;
import io.bloogames.deckbuilder.vfx.VisualEffect;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static io.bloogames.deckbuilder.manager.CustomActions.tint;

public class DamageVisualEffect implements VisualEffect {

    private final Action flashAction;
    private final Action damageBubbleAction;
    private final Action shakeAction;
    private final Actor actor;
    private final ResizableContainer damageLabel;

    public DamageVisualEffect(ResizableGroup target, View targetView, int amount) {
        this.actor = target;
        Tint tint = new Tint();
        target.addTint(tint);

        this.flashAction = sequence(tint(tint, Color.RED.toFloatBits(), 0.2f),
            run(targetView::sync),
            delay(0.1f),
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
            scaleTo(1.75f, 1.75f),
            parallel(
                fadeIn(0.1f),
                scaleTo(1f, 1f, 0.1f)
            ),
            delay(0.5f),
            parallel(
                moveBy(0, 50f, 0.5f),
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
        VFXManager.INSTANCE.addToTextGroup(damageLabel);

        Vector2 localPosition = new Vector2();
        Vector2 stagePosition = actor.localToActorCoordinates(VFXManager.INSTANCE.getVfxActorGroup(), localPosition);
        damageLabel.setBounds(stagePosition.x, stagePosition.y, actor.getWidth(), actor.getHeight());
        damageLabel.setOrigin(Align.center);
        damageLabel.addAction(damageBubbleAction);
    }

    @Override
    public boolean isReady() {
        return flashAction.getActor() == null && shakeAction.getActor() == null;
    }
}
