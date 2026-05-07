package io.bloogames.deckbuilder.model.stats;

import io.bloogames.deckbuilder.data.BaseStats;
import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.text.Describable;
import io.bloogames.deckbuilder.text.DescriptionProperties;

public class StatChanges implements Describable {
    DescriptionProperties descriptionProperties = new DescriptionProperties();
    private int power = 0;
    private int health = 0;

    public StatChanges() {
    }

    public StatChanges(BaseStats baseStats) {
        this(baseStats.power(), baseStats.health());
    }

    public StatChanges(StatChanges statChanges) {
        this(statChanges.getHealth(), statChanges.getPower());
    }

    public StatChanges(int power, int health) {
        this.health = health;
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void changeHealthBy(int amount) {
        this.health += amount;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void changePowerBy(int amount) {
        this.power += amount;
    }

    public void changeBy(StatChanges changes) {
        changePowerBy(changes.getPower());
        changeHealthBy(changes.getHealth());
    }

    @Override
    public String description() {
        descriptionProperties.registerSignedInt("power_mod", getPower());
        descriptionProperties.registerSignedInt("health_mod", getHealth());
        if (getPower() != 0 && getHealth() != 0) {
            return TextManager.INSTANCE.getCommonText("stats_change_both", descriptionProperties);
        } else if (getPower() != 0) {
            return TextManager.INSTANCE.getCommonText("stats_change_power", descriptionProperties);
        }
        return TextManager.INSTANCE.getCommonText("stats_change_health", descriptionProperties);
    }
}
