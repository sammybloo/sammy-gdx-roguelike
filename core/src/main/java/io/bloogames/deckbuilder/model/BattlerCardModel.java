package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseBattlerCard;

public class BattlerCardModel extends CardModel {
    private final BaseBattlerCard baseBattlerCard;
    private final Array<Aura> auras;

    public BattlerCardModel(BaseBattlerCard base) {
        super(base);
        this.baseBattlerCard = base;
        auras = baseBattlerCard.getAuras();
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
        arr.addAll(auras);
    }
}
