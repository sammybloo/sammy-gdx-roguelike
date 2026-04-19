package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.BaseActionCard;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.model.ActionCardModel;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.view.ActionCardView;
import io.bloogames.deckbuilder.view.BattlerCardView;
import io.bloogames.deckbuilder.view.CardView;

public enum CardManager {
    INSTANCE;

    ObjectMap<String, BaseBattlerCard> battlerCardObjectMap;
    ObjectMap<String, BaseActionCard> actionCardObjectMap;

    CardManager() {
        battlerCardObjectMap = new ObjectMap<>();
        BattlerCards.registerAll(battlerCardObjectMap);

        actionCardObjectMap = new ObjectMap<>();
        ActionCards.registerAll(actionCardObjectMap);
    }

    public CardView getCard(CardModel cardModel) {
        if (cardModel instanceof BattlerCardModel) {
            return new BattlerCardView((BattlerCardModel) cardModel);
        }

        if (cardModel instanceof ActionCardModel) {
            return new ActionCardView((ActionCardModel) cardModel);
        }

        return null;
    }

    public BaseBattlerCard getBattlerCard(String id) {
        return battlerCardObjectMap.get(id);
    }

    public BaseActionCard getActionCard(String id) {
        return actionCardObjectMap.get(id);
    }
}
