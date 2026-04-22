package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.PartyModel;

public class CardTarget implements Target {

    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.CARD}
    );
    private PartyModel owner;
    private CardModel card;

    public CardTarget(CardModel card, PartyModel owner) {
        this.owner = owner;
        this.card = card;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public CardModel card() {
        return card;
    }
}
