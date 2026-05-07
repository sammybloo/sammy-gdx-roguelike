package io.bloogames.deckbuilder.model.aura.concrete;

import io.bloogames.deckbuilder.data.DamageType;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.number.Amount;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.model.damage.DamageModifier;
import io.bloogames.deckbuilder.text.DescriptionProperties;

public class IncreaseSpellDamageAura extends AuraModel {
    public static final String ID = "increase_spell_damage";
    Amount amount;

    public IncreaseSpellDamageAura(Amount amount) {
        super(ID);
        this.amount = amount;
    }

    @Override
    public void beforeDamage(TargetContext<DamageableTarget> damageableTargetContext, Damage damage) {
        if (damage.getDamageType().equals(DamageType.SPELL)) {
            damage.addModifier(new DamageModifier() {
                @Override
                public float calculate(Damage damage, float currentAmount) {
                    return currentAmount + amount.getCurrentInt();
                }

                @Override
                public Priority priority() {
                    return Priority.ADD;
                }
            });
        }
    }

    @Override
    protected void registerProperties(DescriptionProperties properties) {
        properties.registerDescribable("increase", amount);
    }

    @Override
    public AuraModel copy() {
        return new IncreaseSpellDamageAura(amount);
    }
}
