package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class BattlerCardModel extends CardModel {
    private final BaseBattlerCard baseBattlerCard;
    private final AuraSet auraSet;

    public BattlerCardModel(BaseBattlerCard base, Ownership.Type owner) {
        super(base, owner);
        this.baseBattlerCard = base;
        auraSet = new AuraSet(baseBattlerCard.getAuras());
    }

    public int getPower() {
        return baseBattlerCard.getBaseStats().power();
    }

    public int getHealth() {
        return baseBattlerCard.getBaseStats().health();
    }

    public BaseBattlerCard getBaseBattlerCard() {
        return baseBattlerCard;
    }

    public void addAllAuras(Array<Aura> arr) {
        arr.addAll(auraSet.getAuras());
    }
}
