package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;

public class LeaderMessageView extends ResizableGroup {
    private final Image speechBubble;
    private final Label messageLabel;

    public LeaderMessageView() {
        super(600, 300);
        speechBubble = new Image(new NinePatchDrawable(AssetManager.INSTANCE.getNinePatch("speechbubble")));
        messageLabel = new Label("Hello this is some default text",
            new Label.LabelStyle(FontManager.INSTANCE.getLeaderMessageFont(), null));
        messageLabel.setWrap(false);
        messageLabel.setAlignment(Align.center);
        speechBubble.setName("test1");
        register(speechBubble, new ResizeableSettings(600, 300, Align.center));
        register(messageLabel, new ResizeableSettings(600, 300, Align.top));
    }
}
