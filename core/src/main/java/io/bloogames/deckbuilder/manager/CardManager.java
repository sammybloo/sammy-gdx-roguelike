package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.view.BattlerCardView;
import io.bloogames.deckbuilder.view.CardView;

public enum CardManager {
    INSTANCE;

    ObjectMap<String, BaseBattlerCard> battlerCardObjectMap;

    CardManager() {
        battlerCardObjectMap = new ObjectMap<>();
        BattlerCards.registerAll(battlerCardObjectMap);
    }

    public CardView getCard(CardModel cardModel) {
        if (cardModel instanceof BattlerCardModel) {
            return new BattlerCardView((BattlerCardModel) cardModel);
        }

        return null;
    }

    public BaseBattlerCard getBattlerCard(String id) {
        return battlerCardObjectMap.get(id);
    }
}
