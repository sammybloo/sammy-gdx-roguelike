package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class Card extends Group {
    CardModel cardModel;

    public Card(CardModel cardModel) {
        this.cardModel = cardModel;
    }
}
