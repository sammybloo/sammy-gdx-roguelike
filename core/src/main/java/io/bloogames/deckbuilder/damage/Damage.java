package io.bloogames.deckbuilder.damage;

import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class Damage {
    private int baseAmount;
    private Array<DamageModifier> modifiers = new Array<>();
    private DamageType damageType;

    public Damage(DamageType type, int baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void addModifier(DamageModifier modifier) {
        modifiers.add(modifier);
    }

    public void sortModifiers() {
        modifiers.sort(Comparator.comparingInt(DamageModifier::priority).reversed());
    }

    public void clearModifiers() {
        modifiers.clear();
    }

    public int getAmount() {
        int amount = baseAmount;

        sortModifiers();

        for (DamageModifier modifier : modifiers) {
            amount = modifier.apply(this, amount);
        }

        return amount;
    }

    public enum DamageType {ATTACK, SPELL, EFFECT}
}
