package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.data.AuraSupplier;
import io.bloogames.deckbuilder.data.BaseBattlerCard;
import io.bloogames.deckbuilder.data.BaseStats;

public class BattlerCards {
    public static void registerAll(ObjectMap<String, BaseBattlerCard> objectMap) {
        register(objectMap, new BaseBattlerCard("battler", "Mrs Battle", 1,
            new BaseStats(3, 5), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("beetle", "Buggo", 2,
            new BaseStats(7, 8), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("bird", "Da Bird", 3,
            new BaseStats(6, 2), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("fallenstar", "Fallen Star", 3,
            new BaseStats(99, 99), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("wrio", "Warm Wriothesley", 3,
            new BaseStats(6, 0), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("vanille", "Lesbean", 3,
            new BaseStats(3, 10), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("columbo", "Columbno Glasses", 3,
            new BaseStats(5, 5), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("snail", "Snaul", 3,
            new BaseStats(12, 1), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("paulallen", "Harvey Normal", 3,
            new BaseStats(2, 2), AuraSupplier.empty));
        register(objectMap, new BaseBattlerCard("worms", "Vent Worms", 3,
            new BaseStats(4, 5), AuraSupplier.empty));
    }

    public static void register(ObjectMap<String, BaseBattlerCard> objectMap, BaseBattlerCard card) {
        objectMap.put(card.getCardId(), card);
    }
}
