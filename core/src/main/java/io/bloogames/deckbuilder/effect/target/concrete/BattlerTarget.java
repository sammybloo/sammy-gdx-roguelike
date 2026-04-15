package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlerModel;
import io.bloogames.deckbuilder.model.PartyModel;

public final class BattlerTarget implements Target {
    private final BattlerModel battler;
    private final PartyModel owner;

    public BattlerTarget(BattlerModel battler, PartyModel owner) {
        this.battler = battler;
        this.owner = owner;
    }

    @Override
    public TargetType type() {
        return TargetType.BATTLER;
    }

    public BattlerModel battler() {
        return battler;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }
}
