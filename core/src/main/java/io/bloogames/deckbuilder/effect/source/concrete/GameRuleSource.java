package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.ModelProperties;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public record GameRuleSource(GameModel model) implements Source {
    @Override
    public Ownership.Type owner() {
        return Ownership.Type.NONE;
    }

    @Override
    public ModelProperties modelProperties() {
        return ModelProperties.EMPTY;
    }
}
