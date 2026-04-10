package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.SlotModel;

public final class SlotTarget implements Target {
    private final SlotModel slot;
    private final PartyModel owner;

    public SlotTarget(SlotModel slot, PartyModel owner) {
        this.slot = slot;
        this.owner = owner;
    }

    @Override
    public TargetType type() {
        return TargetType.SLOT;
    }

    public SlotModel slot() {
        return slot;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }
}
