package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class ActionCardModel extends CardModel {
    public ActionCardModel(BaseCard base, Ownership.Type owner) {
        super(base, owner);
    }
}
