package io.bloogames.deckbuilder.effect.target.concrete;

import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.BattlerModel;

public final class BattlerTarget implements Target {
    private final BattlerModel battler;

    public BattlerTarget(BattlerModel battler) {
        this.battler = battler;
    }

    @Override
    public TargetType type() {
        return TargetType.BATTLER;
    }

    public BattlerModel battler() {
        return battler;
    }
}
