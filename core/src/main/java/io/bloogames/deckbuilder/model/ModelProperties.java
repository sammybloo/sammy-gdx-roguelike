package io.bloogames.deckbuilder.model;

import io.bloogames.deckbuilder.data.BaseProperties;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.number.Amount;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.model.stats.StatsModifier;
import io.bloogames.deckbuilder.text.Describable;
import io.bloogames.deckbuilder.text.DescriptionProperties;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ModelProperties {
    public static final ModelProperties EMPTY = new ModelProperties(BaseProperties.EMPTY);

    private final Map<String, Damage> damageMap;
    private final Map<String, StatsModifier> statsModifierMap;
    private final Map<String, Amount> amountMap;

    public ModelProperties(BaseProperties baseProperties) {
        this.damageMap = baseProperties.damageMap().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> new Damage(entry.getValue())
            ));

        this.statsModifierMap = baseProperties.statsMap().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().get()
            ));

        this.amountMap = baseProperties.amountMap().entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().get()
            ));
    }

    public Damage getDamage(String id) {
        Damage damage = damageMap.get(id);
        if (damage == null) {
            throw new NoSuchElementException("No damage property exists for id: " + id);
        }
        return damage;
    }

    public StatsModifier getStatsModifier(String id) {
        StatsModifier statsModifier = statsModifierMap.get(id);
        if (statsModifier == null) {
            throw new NoSuchElementException("No stats modifier property exists for id: " + id);
        }
        return statsModifier;
    }

    public Amount getAmount(String id) {
        Amount amount = amountMap.get(id);
        if (amount == null) {
            throw new NoSuchElementException("No amount property exists for id: " + id);
        }
        return amount;
    }

    public void updateAllProperties(TargetContext<?> context) {
        for (Damage damage : damageMap.values()) {
            DamageableTarget target = context.target() instanceof DamageableTarget t ? t : null;
            context.game().getDamageCoordinator().calculateDamage(context.source(), target, damage);
        }

        for (Amount amount : amountMap.values()) {
            amount.calculateNumber(context);
        }
    }

    public void registerAllProperties(DescriptionProperties descriptionProperties) {
        registerAll(descriptionProperties, damageMap);
        registerAll(descriptionProperties, statsModifierMap);
        registerAll(descriptionProperties, amountMap);
    }

    private void registerAll(DescriptionProperties descriptionProperties, Map<String, ? extends Describable> describables) {
        describables.forEach(descriptionProperties::registerDescribable);
    }
}
