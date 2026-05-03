package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LeaderMessageView extends ResizableGroup {
    private final Label label;

    public LeaderMessageView() {
        super(600, 200);
        label = new Label("",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderMessageFont(), null));
        label.setWrap(true);
        label.setAlignment(Align.center);
        label.setName("test1");
        label.setTouchable(Touchable.disabled);

        setBackground(AssetManager.INSTANCE.getNinePatch("speechbubble"));
        register(label, new ResizableSettings(600, 180, Align.bottom).paddingY(10));
        setVisible(false);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cancelMessage();
            }
        });
    }

    public void cancelMessage() {
        clearActions();
        setVisible(false);
    }

    public void showMessage(String message) {
        if (isVisible() && message.equalsIgnoreCase(label.getText().toString())) {
            return;
        }
        label.setText(message);
        clearActions();
        addAction(sequence(
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
