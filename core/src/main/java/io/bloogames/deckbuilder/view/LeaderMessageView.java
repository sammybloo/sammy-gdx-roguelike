package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.SpeechBubble;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LeaderMessageView extends ResizableGroup {
    private final SpeechBubble speechBubble;

    public LeaderMessageView() {
        super(600, 100);
        NinePatch patch = AssetManager.INSTANCE.getNinePatch("speechbubble");
        Label messageLabel = new Label("",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderMessageFont(), null));
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);

        speechBubble = new SpeechBubble(patch, messageLabel, 600, 100);
        register(speechBubble, new ResizeableSettings(600, 100));
        speechBubble.setVisible(false);
        speechBubble.setPadding(24, 24, 24, 36);
        setTouchable(Touchable.childrenOnly);

        speechBubble.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cancelMessage();
            }
        });
    }

    public void cancelMessage() {
        speechBubble.clearActions();
        speechBubble.setVisible(false);
    }

    public void showMessage(String message) {
        speechBubble.getLabel().setText(message);
        speechBubble.clearActions();
        speechBubble.addAction(sequence(
            alpha(0),
            visible(true),
            scaleTo(0.95f, 0.95f),
            parallel(
                fadeIn(0.1f),
                scaleTo(1f, 1f, 0.1f)
            ),
            delay(1.5f),
            parallel(
                fadeOut(0.1f),
                scaleTo(0.95f, 0.95f, 0.1f)
            ),
            visible(false)
        ));
    }
}
