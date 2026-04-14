package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.BattlePartyModel;

public final class LeaderTarget implements Target {
    private final LeaderModel leader;
    private final BattlePartyModel owner;

    public LeaderTarget(LeaderModel leader, BattlePartyModel owner) {
        this.leader = leader;
        this.owner = owner;
    }

    @Override
    public TargetType type() {
        return TargetType.LEADER;
    }

    public LeaderModel leader() {
        return leader;
    }

    @Override
    public BattlePartyModel owner() {
        return owner;
    }
}
