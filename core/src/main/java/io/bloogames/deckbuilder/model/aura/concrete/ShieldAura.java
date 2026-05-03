package io.bloogames.deckbuilder.model.aura.concrete;

import com.badlogic.gdx.math.MathUtils;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.aura.StackableAura;
import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.model.damage.DamageModifier;

public class ShieldAura extends StackableAura {
    private static final String ID = "shield";

    public ShieldAura(int stacks) {
        super(ID, stacks);
    }

    @Override
    public void beforeDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {
        ShieldAura that = this;
        damage.addModifier(new DamageModifier() {
            @Override
            public float calculate(Damage damage, float currentAmount) {
                return Math.max(0, currentAmount - getStacks());
            }

            @Override
            public float apply(TargetContext<DamageableTarget> context, Damage damage, float currentAmount) {
                float result = calculate(damage, currentAmount);
                int amountRemoved = MathUtils.ceil(result - currentAmount);
                removeStacks(amountRemoved);
                context.game().dispatch(new GameEvent.AuraModifiedEvent(that));
                return result;
            }

            @Override
            public Priority priority() {
                return Priority.SHIELD;
            }
        });
    }

    @Override
    public AuraModel copy() {
        return new ShieldAura(getStacks());
    }

    @Override
    public String description() {
        throw new UnsupportedOperationException("TODO implement Shield aura description");
    }
}
