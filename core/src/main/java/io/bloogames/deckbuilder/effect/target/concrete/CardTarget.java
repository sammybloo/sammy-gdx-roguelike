package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class CardTarget implements Target {

    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.CARD}
    );
    private final CardModel card;

    public CardTarget(CardModel card) {
        this.card = card;
    }

    @Override
    public Ownership.Type owner() {
        return card.getOwnership().getCurrentOwner();
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public CardModel card() {
        return card;
    }
}
