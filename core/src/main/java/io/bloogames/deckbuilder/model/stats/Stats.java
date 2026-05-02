package io.bloogames.deckbuilder.model.stats;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseStats;

import java.util.Comparator;

public class Stats {
    private final BaseStats baseStats;
    private final StatChanges permanentStatChanges = new StatChanges();
    private final Array<StatsModifier> modifiers = new Array<>();

    public Stats(BaseStats baseStats) {
        this.baseStats = baseStats;
    }

    public BaseStats getBaseStats() {
        return baseStats;
    }

    public void addModifier(StatsModifier modifier) {
        modifiers.add(modifier);
    }

    public void clearModifiers() {
        modifiers.clear();
    }

    public void permanentlyChange(StatChanges changes) {
        permanentStatChanges.changeBy(changes);
    }

    public void sortModifiers() {
        modifiers.sort(Comparator.comparingInt((StatsModifier modifier) -> modifier.priority().getSpeed()).reversed());
    }

    private StatChanges getStatChanges() {
        sortModifiers();
        StatChanges statChanges = new StatChanges(permanentStatChanges);
        for (StatsModifier modifier : modifiers) {
            modifier.calculate(this, statChanges);
        }
        return statChanges;
    }

    public CurrentStats getCurrentStats() {
        StatChanges statChanges = getStatChanges();
        return new CurrentStats(statChanges, baseStats.health() + statChanges.getHealth(), baseStats.power() + statChanges.getPower());
    }

    public record CurrentStats(StatChanges statChanges, int health, int power) {
    }
}
