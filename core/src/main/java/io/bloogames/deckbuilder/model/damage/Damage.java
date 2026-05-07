package io.bloogames.deckbuilder.model.damage;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseDamage;
import io.bloogames.deckbuilder.data.DamageType;
import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.text.Describable;

import java.util.Comparator;
import java.util.Optional;

public class Damage implements Describable {
    private final Array<DamageModifier> modifiers = new Array<>();
    private final Array<DamagePreventer> preventers = new Array<>();
    private BaseDamage baseDamage;

    public Damage(BaseDamage baseDamage) {
        this.baseDamage = baseDamage;
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
        return baseDamage.getAmount();
    }

    public int getAmount() {
        float amount = getBaseAmount();

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

    public DamageType getDamageType() {
        return baseDamage.getDamageType();
    }

    @Override
    public String description() {
        return getAmount() + TextManager.INSTANCE.getCommonTextTemplate("damage");
    }
}
