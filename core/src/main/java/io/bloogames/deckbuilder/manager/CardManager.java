package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.card.ActionCards;
import io.bloogames.deckbuilder.card.BattlerCards;
import io.bloogames.deckbuilder.data.BaseActionCard;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.model.ActionCardModel;
import io.bloogames.deckbuilder.model.BattlerCardModel;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;
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

    public CardModel getCardModel(String id, Ownership.Type owner) {
        BaseCard baseCard = getActionCard(id);
        if (baseCard == null) {
            baseCard = getBattlerCard(id);
        }

        if (baseCard instanceof BaseBattlerCard baseBattlerCard) {
            return new BattlerCardModel(baseBattlerCard, owner);
        } else if (baseCard instanceof BaseActionCard baseActionCard) {
            return new ActionCardModel(baseActionCard, owner);
        }
        return null;
    }

    public CardView getCardView(CardModel cardModel) {
        if (cardModel instanceof BattlerCardModel battlerCardModel) {
            return new BattlerCardView(battlerCardModel);
        }

        if (cardModel instanceof ActionCardModel actionCardModel) {
            return new ActionCardView(actionCardModel);
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
