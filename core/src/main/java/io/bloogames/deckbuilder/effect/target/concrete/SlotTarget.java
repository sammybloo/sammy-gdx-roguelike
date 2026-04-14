package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.SlotModel;

public final class SlotTarget implements Target {
    private final SlotModel slot;
    private final BattlePartyModel owner;

    public SlotTarget(SlotModel slot, BattlePartyModel owner) {
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
    public BattlePartyModel owner() {
        return owner;
    }
}
