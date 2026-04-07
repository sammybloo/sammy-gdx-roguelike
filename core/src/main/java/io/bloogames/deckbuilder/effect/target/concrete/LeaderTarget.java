package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.LeaderModel;

public final class LeaderTarget implements Target {
    private final LeaderModel leader;

    public LeaderTarget(LeaderModel leader) {
        this.leader = leader;
    }

    @Override
    public TargetType type() {
        return TargetType.LEADER;
    }

    public LeaderModel leader() {
        return leader;
    }
}
