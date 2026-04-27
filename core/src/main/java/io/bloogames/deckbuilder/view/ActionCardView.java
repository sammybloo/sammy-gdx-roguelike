package io.bloogames.deckbuilder.view;

import io.bloogames.deckbuilder.model.ActionCardModel;

public class ActionCardView extends CardView {
    public ActionCardView(ActionCardModel cardModel) {
        super(cardModel, "actionframe", "card/" + cardModel.getCardId());

        sync();
    }
}
