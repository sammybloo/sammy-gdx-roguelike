package io.bloogames.deckbuilder.model.aura.concrete;

import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.stats.StatChanges;
import io.bloogames.deckbuilder.model.stats.StatsModifier;

public class AmbientStatChangeForAlliesAura extends Aura {

    StatsModifier statsModifier;

    public AmbientStatChangeForAlliesAura(String id, StatsModifier statsModifier) {
        super(id);
        this.statsModifier = statsModifier;
    }

    @Override
    public void onCalculateStats(GameModel game, BattlerTarget battler) {
        if (getOwner().source().model() != battler.battler() && getOwner().source().owner().isOwn(battler.owner())) {
            battler.battler().getStats().addModifier(statsModifier);
        }
    }

    @Override
    public Aura copy() {
        return new AmbientStatChangeForAlliesAura(getId(), statsModifier);
    }
}
