package io.bloogames.deckbuilder.view;

import io.bloogames.deckbuilder.model.CardModel;

public class ActionCardView extends CardView {
    public ActionCardView(CardModel cardModel) {
        super(cardModel, "actionframe", "card/" + cardModel.getCardId());
    }
}
