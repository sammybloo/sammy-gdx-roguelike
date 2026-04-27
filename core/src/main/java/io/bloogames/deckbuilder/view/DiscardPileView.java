package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.DiscardPileModel;
import io.bloogames.deckbuilder.scene2d.HoverListener;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.View;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class DiscardPileView extends ResizableGroup implements View {
    public static final float WIDTH = 205;
    public static final float HEIGHT = 105;

    private final DiscardPileModel model;
    private final Image image;
    private Label discardPileLabel;

    public DiscardPileView(DiscardPileModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
        this.image = new Image(AssetManager.INSTANCE.findRegion("discardpile"));

        register(image, new ResizableSettings(WIDTH, HEIGHT));
        setupLabel();
    }


    public void setupLabel() {
        discardPileLabel = new Label(model.size() + "",
            new Label.LabelStyle(FontManager.INSTANCE.getDiscardPileSizeFont(), null));
        discardPileLabel.setAlignment(Align.center, Align.center);
        discardPileLabel.setVisible(false);

        addListener(new HoverListener(0f, 0f) {
            @Override
            public void onHoverStart(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                discardPileLabel.addAction(
                    sequence(
                        visible(true),
                        fadeIn(0.1f)
                    ));
            }

            @Override
            public void onHoverEnd(InputEvent event, float x, float y, int pointer, Actor toActor) {
                discardPileLabel.addAction(
                    sequence(
                        fadeOut(0.1f),
                        visible(false)
                    ));
            }
        });

        register(discardPileLabel, new ResizableSettings(WIDTH, HEIGHT));
    }

    @Override
    public void sync() {
        discardPileLabel.setText(model.size());
    }
}
