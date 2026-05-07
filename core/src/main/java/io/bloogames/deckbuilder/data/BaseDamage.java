package io.bloogames.deckbuilder.data;

public class BaseDamage {
    private final int amount;
    private final DamageType damageType;

    public BaseDamage(int amount, DamageType damageType) {
        this.amount = amount;
        this.damageType = damageType;
    }

    public int getAmount() {
        return amount;
    }

    public DamageType getDamageType() {
        return damageType;
    }
}
