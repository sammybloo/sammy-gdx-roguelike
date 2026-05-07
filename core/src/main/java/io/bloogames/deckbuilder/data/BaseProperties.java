package io.bloogames.deckbuilder.data;

import io.bloogames.deckbuilder.effect.number.Amount;
import io.bloogames.deckbuilder.model.stats.StatsModifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public record BaseProperties(Map<String, BaseDamage> damageMap, Map<String, Supplier<StatsModifier>> statsMap,
                             Map<String, Supplier<Amount>> amountMap) {
    public static BaseProperties EMPTY = new BaseProperties(new HashMap<>(), new HashMap<>(), new HashMap<>());

    public BaseProperties(Map<String, BaseDamage> damageMap, Map<String, Supplier<StatsModifier>> statsMap, Map<String, Supplier<Amount>> amountMap) {
        this.damageMap = Map.copyOf(damageMap);
        this.statsMap = Map.copyOf(statsMap);
        this.amountMap = Map.copyOf(amountMap);
    }

    public static class Builder {
        private final Map<String, BaseDamage> damageMap = new HashMap<>();
        private final Map<String, Supplier<StatsModifier>> statsMap = new HashMap<>();
        private final Map<String, Supplier<Amount>> amountMap = new HashMap<>();

        public static Builder create() {
            return new Builder();
        }

        public Builder damage(String id, BaseDamage damage) {
            damageMap.put(id, damage);
            return this;
        }

        public Builder stats(String id, Supplier<StatsModifier> stats) {
            statsMap.put(id, stats);
            return this;
        }

        public Builder amount(String id, Supplier<Amount> amount) {
            amountMap.put(id, amount);
            return this;
        }

        public BaseProperties build() {
            return new BaseProperties(damageMap, statsMap, amountMap);
        }
    }
}
