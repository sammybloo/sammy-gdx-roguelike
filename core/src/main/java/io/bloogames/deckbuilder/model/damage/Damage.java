package io.bloogames.deckbuilder.model.damage;

import com.badlogic.gdx.utils.Array;

import java.util.Comparator;
import java.util.Optional;

public class Damage {
    private final int baseAmount;
    private final Array<DamageModifier> modifiers = new Array<>();
    private final Array<DamagePreventer> preventers = new Array<>();
    private DamageType damageType;

    public Damage(DamageType type, int baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void addModifier(DamageModifier modifier) {
        modifiers.add(modifier);
    }

    public void sortModifiers() {
        modifiers.sort(Comparator.comparingInt((DamageModifier modifier) -> modifier.priority().getSpeed()).reversed());
    }

    public void sortPreventers() {
        preventers.sort(Comparator.comparingInt((DamagePreventer preventer) -> preventer.priority().getSpeed()).reversed());
    }

    public void clear() {
        modifiers.clear();
        preventers.clear();
    }

    public int getBaseAmount() {
        return baseAmount;
    }

    public int getAmount() {
        float amount = baseAmount;

        sortModifiers();

        for (DamageModifier modifier : modifiers) {
            amount = modifier.calculate(this, amount);
        }

        return Math.round(amount);
    }

    public Optional<DamagePreventer> getPreventer() {
        if (preventers.size == 0) {
           return Optional.empty();
        }

        sortPreventers();

        return Optional.of(preventers.get(0));
    }

    public enum DamageType {ATTACK, SPELL, EFFECT}
}
