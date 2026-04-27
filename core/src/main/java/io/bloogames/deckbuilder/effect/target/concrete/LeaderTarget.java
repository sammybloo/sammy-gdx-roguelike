package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public final class LeaderTarget extends DamageableTarget {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.LEADER, TargetType.DAMAGEABLE}
    );
    private final LeaderModel leader;

    public LeaderTarget(LeaderModel leader) {
        super(leader);
        this.leader = leader;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public LeaderModel leader() {
        return leader;
    }

    @Override
    public Ownership.Type owner() {
        return leader.getOwnership().getCurrentOwner();
    }
}
