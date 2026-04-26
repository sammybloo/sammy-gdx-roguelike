package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.damage.Damage;
import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.BattlerTarget;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public abstract class Aura {
    public void beforeDamage(SourceContext<?> sourceContext, TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {}
    public void afterDamage(SourceContext<?> sourceContext, TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {}
    public void onCalculateStats(TargetContext<BattlerTarget> battlerContext, StatsModel stats) {}

    public abstract Aura copy();
}
