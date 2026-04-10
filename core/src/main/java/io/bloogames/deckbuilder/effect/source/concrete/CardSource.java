package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.EffectSource;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.model.LeaderModel;

public class CardSource implements EffectSource {
    private final CardModel card;
    private final LeaderModel caster;

    public CardSource(CardModel card, LeaderModel caster) {
        this.card = card;
        this.caster = caster;
    }

    // TODO: find a different ID for this that is actually unique
    @Override
    public String sourceId() {
        return card.getCardId();
    }

    public CardModel card() {
        return card;
    }

    public LeaderModel caster() {
        return caster;
    }
}
