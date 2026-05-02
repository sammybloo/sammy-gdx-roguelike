package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.AuraModel;

public class BaseLeader {
    private final String id;
    private final int maxHealth;
    private final int maxMana;
    private final AuraSupplier auraSupplier;

    public BaseLeader(String id, int maxHealth, int maxMana, Array<AuraModel> auras) {
        this.id = id;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.auraSupplier = new AuraSupplier(auras);
    }

    public String getId() {
        return id;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public Array<AuraModel> getAuras() {
        return auraSupplier.get();
    }
}
