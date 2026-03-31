package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.bloogames.deckbuilder.scene2d.HoverListener;

public abstract class Card extends Group {
    private CardModel cardModel;

    public Card(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public CardModel getCardModel() {
        return cardModel;
    }
}
