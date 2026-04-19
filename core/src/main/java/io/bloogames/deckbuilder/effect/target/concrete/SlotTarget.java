package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.SlotModel;

public final class SlotTarget implements Target {
    private final SlotModel slot;
    private final PartyModel owner;
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[] {TargetType.SLOT}
    );

    public SlotTarget(SlotModel slot, PartyModel owner) {
        this.slot = slot;
        this.owner = owner;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public SlotModel slot() {
        return slot;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }
}
