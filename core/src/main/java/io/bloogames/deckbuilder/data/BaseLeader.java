package io.bloogames.deckbuilder.data;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.Aura;

public class BaseLeader {
    private String id;
    private int maxHealth;
    private int maxMana;
    private AuraSupplier auraSupplier;

    public BaseLeader(String id, int maxHealth, int maxMana, Array<Aura> auras) {
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

    public Array<Aura> getAuras() {
        return auraSupplier.get();
    }
}
