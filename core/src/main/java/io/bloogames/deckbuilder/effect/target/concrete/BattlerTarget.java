package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.PartyModel;

public final class BattlerTarget extends DamageableTarget {
    private final BattlerModel battler;
    private final PartyModel owner;
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[] { TargetType.BATTLER, TargetType.DAMAGEABLE}
    );

    public BattlerTarget(BattlerModel battler, PartyModel owner) {
        super(battler, owner);
        this.battler = battler;
        this.owner = owner;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public BattlerModel battler() {
        return battler;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }
}
