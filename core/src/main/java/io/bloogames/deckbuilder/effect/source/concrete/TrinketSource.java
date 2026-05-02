package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.TrinketModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public record TrinketSource(TrinketModel model) implements Source {
    @Override
    public Ownership.Type owner() {
        return model.getOwnership().getCurrentOwner();
    }
}
