package io.bloogames.deckbuilder.model.stats.concrete;

import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.model.stats.StatChanges;
import io.bloogames.deckbuilder.model.stats.Stats;
import io.bloogames.deckbuilder.model.stats.StatsModifier;
import io.bloogames.deckbuilder.text.ModelProperties;

public class FlatStatsModifier extends StatsModifier {
    public final StatChanges statChanges;
    ModelProperties modelProperties = new ModelProperties();

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

    @Override
    public String description() {
        modelProperties.registerSignedInt("{power_mod}", statChanges.getPower());
        modelProperties.registerSignedInt("{health_mod}", statChanges.getHealth());
        if (statChanges.getPower() != 0 && statChanges.getHealth() != 0) {
            return TextManager.INSTANCE.getCommonText("stats_change_both", modelProperties);
        }
        else if (statChanges.getPower() != 0) {
            return TextManager.INSTANCE.getCommonText("stats_change_power", modelProperties);
        }
        return TextManager.INSTANCE.getCommonText("stats_change_health", modelProperties);
    }
}
