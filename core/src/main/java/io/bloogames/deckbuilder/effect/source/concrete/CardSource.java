package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.model.CardModel;

public final class CardSource implements EffectSource {
    private final CardModel card;

    public CardSource(CardModel card) {
        this.card = card;
    }

    // TODO: find a different ID for this that is actually unique
    @Override
    public String sourceId() {
        return card.getCardId();
    }

    public CardModel card() {
        return card;
    }
}
