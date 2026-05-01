package io.bloogames.deckbuilder.effect.source.concrete;

import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public record SlotSource(SlotModel model) implements Source {
    @Override
    public Ownership.Type owner() {
        return model.getOwnership().getCurrentOwner();
    }
}
