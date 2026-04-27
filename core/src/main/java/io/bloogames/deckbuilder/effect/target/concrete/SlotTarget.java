package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.SlotModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public record SlotTarget(SlotModel slot) implements Target {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.SLOT}
    );

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    @Override
    public Ownership.Type owner() {
        return slot.getOwnership().getCurrentOwner();
    }
}
