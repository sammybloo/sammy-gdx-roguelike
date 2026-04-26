package io.bloogames.deckbuilder.damage;

import io.bloogames.deckbuilder.effect.context.SourceContext;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;

public interface DamagePreventer {

    boolean applies(SourceContext<?> sourceContext, TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    void prevent(SourceContext<?> sourceContext, TargetContext<DamageableTarget> damageableTargetContext, Damage damage);

    int priority();
}
