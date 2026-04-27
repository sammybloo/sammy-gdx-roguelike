package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public record BattlePartyTarget(BattlePartyModel battleParty) implements Target {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.BATTLE_PARTY}
    );

    @Override
    public Ownership.Type owner() {
        return battleParty.getOwnership().getCurrentOwner();
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }
}
