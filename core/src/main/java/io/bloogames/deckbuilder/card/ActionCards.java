package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.BaseActionCard;
import io.bloogames.deckbuilder.data.BaseDamage;
import io.bloogames.deckbuilder.data.BaseProperties;
import io.bloogames.deckbuilder.data.DamageType;
import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;
import io.bloogames.deckbuilder.effect.step.concrete.ChangeBattlerStatsStep;
import io.bloogames.deckbuilder.effect.step.concrete.DamageStep;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.stats.StatChanges;
import io.bloogames.deckbuilder.model.stats.concrete.FlatStatsModifier;

public class ActionCards {
    public static void registerAll(ObjectMap<String, BaseActionCard> objectMap) {
        var fireball = CardBuilder.create()
            .id("fireball")
            .properties(BaseProperties.Builder.create()
                .damage("damage", new BaseDamage(5, DamageType.SPELL))
                .build())
            .cost(2)
            .effect(
                new TargetedEffect(
                    new TargetSpec(TargetOwnerType.ANY, TargetConditionList.none(), TargetType.DAMAGEABLE),
                    new EffectBuilder()
                        .addTargetStep(TargetType.DAMAGEABLE,
                            (properties) -> new DamageStep(properties.getDamage("damage")))
                        .build())
            )
            .buildAction();

        register(objectMap, fireball);

        var growth = CardBuilder.create()
            .id("grow")
            .properties(BaseProperties.Builder.create()
                .stats("stats", () -> new FlatStatsModifier(new StatChanges(1, 1)))
                .build())
            .cost(1)
            .effect(
                new TargetedEffect(
                    new TargetSpec(TargetOwnerType.ANY, TargetConditionList.none(), TargetType.BATTLER),
                    new EffectBuilder()
                        .addTargetStep(TargetType.BATTLER,
                            (properties -> new ChangeBattlerStatsStep(properties.getStatsModifier("stats"))))
                        .build())
            )
            .buildAction();

        register(objectMap, growth);

//        register(objectMap, new BaseActionCard("grow", 1,
//            new TargetedEffect(
//                new TargetSpec(TargetOwnerType.ANY, TargetConditionList.none(), TargetType.BATTLER),
//                new EffectBuilder()
//                    .addTargetStep(TargetType.BATTLER, () -> new ChangeBattlerStatsStep(new StatChanges(1, 6)))
//                    .build()),
//            SourceConditionList.none(), AuraSupplier.empty));

    }

    public static void register(ObjectMap<String, BaseActionCard> objectMap, BaseActionCard card) {
        objectMap.put(card.getCardId(), card);
    }
}
