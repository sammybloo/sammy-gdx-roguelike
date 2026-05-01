package io.bloogames.deckbuilder.model.stats.concrete;

import io.bloogames.deckbuilder.model.stats.StatChanges;
import io.bloogames.deckbuilder.model.stats.Stats;
import io.bloogames.deckbuilder.model.stats.StatsModifier;

public class FlatStatsModifier implements StatsModifier {
    public final StatChanges statChanges;

    public FlatStatsModifier(StatChanges statChanges) {
        this.statChanges = statChanges;
    }

    @Override
    public void calculate(Stats stats, StatChanges currentChanges) {
        currentChanges.changeBy(statChanges);
    }

    @Override
    public Priority priority() {
        return Priority.ADD_AND_SUBTRACT;
    }
}
