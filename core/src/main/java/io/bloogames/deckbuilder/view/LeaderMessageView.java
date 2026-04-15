package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;

public class LeaderMessageView extends ResizableGroup {
    private final Image speechBubble;
    private final Label messageLabel;

    public LeaderMessageView() {
        super(300, 150);
        speechBubble = new Image(AssetManager.INSTANCE.getNinePatch("speechbubble"));

        messageLabel = new Label("Hello this is some default text",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderMessageFont(), null));
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);
        register(speechBubble, new ResizeableSettings(300, 150, Align.center));
        register(messageLabel, new ResizeableSettings(290, 100, Align.top).yOffset(10));
    }
}
