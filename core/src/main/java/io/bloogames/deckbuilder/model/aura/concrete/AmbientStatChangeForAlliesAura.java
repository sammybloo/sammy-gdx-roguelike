package io.bloogames.deckbuilder.model.aura.concrete;

import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.stats.StatsModifier;

public class AmbientStatChangeForAlliesAura extends AuraModel {

    StatsModifier statsModifier;

    public AmbientStatChangeForAlliesAura(StatsModifier statsModifier) {
        super("stat_change_for_allies_aura");
        this.statsModifier = statsModifier;
    }

    @Override
    public void onCalculateStats(GameModel game, BattlerTarget battler) {
        if (getOwner().source().model() != battler.battler() && getOwner().source().owner().isOwn(battler.owner())) {
            battler.battler().getStats().addModifier(statsModifier);
        }
    }

    @Override
    public AuraModel copy() {
        return new AmbientStatChangeForAlliesAura(statsModifier);
    }
}
