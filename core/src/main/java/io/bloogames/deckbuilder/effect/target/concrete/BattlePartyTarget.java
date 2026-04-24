package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.PartyModel;

public class BattlePartyTarget implements Target {
    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.BATTLE_PARTY}
    );
    private final BattlePartyModel battleParty;

    public BattlePartyTarget(BattlePartyModel battleParty) {
        this.battleParty = battleParty;
    }

    @Override
    public PartyModel owner() {
        return battleParty.getParty();
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public BattlePartyModel battleParty() {
        return battleParty;
    }
}
