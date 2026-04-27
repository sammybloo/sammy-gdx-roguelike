package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class SlotTarget implements Target {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.SLOT}
    );
    private final SlotModel slot;

    public SlotTarget(SlotModel slot) {
        this.slot = slot;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public SlotModel slot() {
        return slot;
    }

    @Override
    public Ownership.Type owner() {
        return slot.getOwnership().getCurrentOwner();
    }
}
