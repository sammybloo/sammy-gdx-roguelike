package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.HighlightState;
import io.bloogames.deckbuilder.ui.Highlightable;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.ViewUtils;

public abstract class CardView extends ResizableGroup implements View, Highlightable {

    public static final float WIDTH = 360f;
    public static final float HEIGHT = 540f;

    private final CardModel cardModel;
    private Image cardBack;

    public CardView(CardModel cardModel) {
        super(WIDTH, HEIGHT);
        this.cardModel = cardModel;
    }

    public CardModel getModel() {
        return cardModel;
    }

    public abstract void hideContents();

    public abstract void showContents();

    public boolean isFaceup() {
        return cardModel.isFaceup();
    }

    public void flipCard(boolean faceup) {
        cardModel.setFaceup(faceup);
        if (faceup) {
            unregister(cardBack);
            showContents();
        } else {
            hideContents();
            if (cardBack == null) {
                cardBack = new Image(AssetManager.INSTANCE.getSprite("cardback"));
            }
            register(cardBack, new ResizeableSettings(WIDTH, HEIGHT, Align.center));
        }
    }

    public void disappear() {
        clearListeners();
        clearActions();
        ViewUtils.unmoor(this);

        addAction(Actions.moveBy(0, 100f, 0.2f));
        addAction(Actions.fadeOut(0.2f));
        addAction(Actions.delay(0.2f, Actions.removeActor()));
    }

    @Override
    public void setHighlightState(HighlightState state) {
        setColor(state.getColour());
    }
}
