package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.PartyModel;

public final class LeaderTarget implements Target {
    private final LeaderModel leader;
    private final PartyModel owner;

    public LeaderTarget(LeaderModel leader, PartyModel owner) {
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
    public PartyModel owner() {
        return owner;
    }
}
