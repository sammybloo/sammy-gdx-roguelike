package io.bloogames.deckbuilder.manager;

import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.view.BattlerCardView;
import io.bloogames.deckbuilder.view.CardView;

public enum CardManager {
    INSTANCE;

    public CardView getCard(CardModel cardModel) {
        if (cardModel instanceof BattlerCardModel) {
            return new BattlerCardView((BattlerCardModel) cardModel);
        }

        return null;
    }
}
