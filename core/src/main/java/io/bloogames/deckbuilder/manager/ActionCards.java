package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.BaseActionCard;
import io.bloogames.deckbuilder.effect.EffectBuilder;
import io.bloogames.deckbuilder.effect.TargetedEffect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;
import io.bloogames.deckbuilder.effect.step.concrete.DamageStep;
import io.bloogames.deckbuilder.effect.target.TargetOwnerType;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.effect.target.TargetType;

public class ActionCards {
    public static void registerAll(ObjectMap<String, BaseActionCard> objectMap) {
        register(objectMap, new BaseActionCard("fireball", "Fireball", 2,
            new TargetedEffect(new TargetSpec(TargetOwnerType.ANY, TargetConditionList.none(), TargetType.DAMAGEABLE),
                new EffectBuilder()
                    .addTargetStep(TargetType.DAMAGEABLE, new DamageStep(5)).build()),
            SourceConditionList.none()));

    }

    public static void register(ObjectMap<String, BaseActionCard> objectMap, BaseActionCard card) {
        objectMap.put(card.getCardId(), card);
    }
}
