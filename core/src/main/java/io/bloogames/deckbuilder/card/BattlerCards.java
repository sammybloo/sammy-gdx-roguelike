package io.bloogames.deckbuilder.card;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.effect.number.ExactAmount;
import io.bloogames.deckbuilder.model.aura.concrete.AmbientStatChangeForAlliesAura;
import io.bloogames.deckbuilder.model.aura.concrete.IncreaseSpellDamageAura;
import io.bloogames.deckbuilder.model.stats.StatChanges;
import io.bloogames.deckbuilder.model.stats.concrete.FlatStatsModifier;

public class BattlerCards {
    public static void registerAll(ObjectMap<String, BaseBattlerCard> objectMap) {
        register(objectMap, CardBuilder.create()
            .id("beetle")
            .cost(2)
            .stats(2, 2)
            .buildBattler());

        register(objectMap, CardBuilder.create()
            .id("battler")
            .cost(3)
            .stats(3, 3)
            .aura(new IncreaseSpellDamageAura(new ExactAmount(1)))
            .buildBattler());


        register(objectMap, CardBuilder.create()
            .id("bird")
            .cost(4)
            .stats(4, 4)
            .aura(new AmbientStatChangeForAlliesAura(new FlatStatsModifier(new StatChanges(1, 1))))
            .buildBattler());
    }

    public static void register(ObjectMap<String, BaseBattlerCard> objectMap, BaseBattlerCard card) {
        objectMap.put(card.getCardId(), card);
    }
}
