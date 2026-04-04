package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.CardModel;

public abstract class Card extends Group {

    public static final float WIDTH = 360f;
    public static final float HEIGHT = 540f;

    private final CardModel cardModel;
    private Image cardBack;
    public Card(CardModel cardModel) {
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
            cardBack.remove();
            showContents();
        }
        else {
            hideContents();
            if (cardBack == null) {
                cardBack = new Image(AssetManager.INSTANCE.getSprite("cardback"));
                cardBack.setSize(getWidth(), getHeight());
            }
            addActor(cardBack);
        }
    }


}
