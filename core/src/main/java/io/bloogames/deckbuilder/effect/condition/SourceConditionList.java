package io.bloogames.deckbuilder.effect.condition;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.source.EffectSource;

public class SourceConditionList<T extends EffectSource> {
    private static final SourceConditionList<?> NONE_INSTANCE = new SourceConditionList<>(new Array<>());

    @SuppressWarnings("unchecked")
    public static <T extends EffectSource> SourceConditionList<T> none() {
        return (SourceConditionList<T>) NONE_INSTANCE;
    }

    private final Array<SourceCondition<? super T>> conditions;

    private SourceConditionList(Array<SourceCondition<? super T>> conditions) {
        this.conditions = conditions;
    }

    public Array<SourceCondition<? super T>> getConditions() {
        return new Array<>(conditions);
    }

    public static <T extends EffectSource> Builder<T> builder() {
        return new Builder<>();
    }

    public static class Builder<T extends EffectSource> {
        private final Array<SourceCondition<? super T>> conditions = new Array<>();

        public Builder<T> add(SourceCondition<? super T> condition) {
            conditions.add(condition);
            return this;
        }

        public SourceConditionList<T> build() {
            return new SourceConditionList<>(conditions);
        }
    }
}
