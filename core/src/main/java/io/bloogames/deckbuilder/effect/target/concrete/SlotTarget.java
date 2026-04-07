package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.SlotModel;

public final class SlotTarget implements Target {
    private final SlotModel slot;

    public SlotTarget(SlotModel slot) {
        this.slot = slot;
    }

    @Override
    public TargetType type() {
        return TargetType.SLOT;
    }

    public SlotModel slot() {
        return slot;
    }
}
