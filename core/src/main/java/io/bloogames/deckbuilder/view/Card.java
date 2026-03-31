package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import io.bloogames.deckbuilder.model.CardModel;

public abstract class Card extends Group {
    private final CardModel cardModel;

    public Card(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    public CardModel getCardModel() {
        return cardModel;
    }
}
