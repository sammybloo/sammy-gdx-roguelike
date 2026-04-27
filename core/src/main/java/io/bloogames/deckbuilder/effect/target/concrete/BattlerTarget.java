package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class BattlerTarget extends DamageableTarget {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.BATTLER, TargetType.DAMAGEABLE}
    );
    private final BattlerModel battler;

    public BattlerTarget(BattlerModel battler) {
        super(battler);
        this.battler = battler;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public BattlerModel battler() {
        return battler;
    }

    @Override
    public Ownership.Type owner() {
        return battler.getOwnership().getCurrentOwner();
    }
}
