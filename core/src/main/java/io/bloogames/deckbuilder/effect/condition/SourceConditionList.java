package io.bloogames.deckbuilder.effect.condition;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.effect.source.Source;

public class SourceConditionList<T extends Source> {
    private static final SourceConditionList<?> NONE_INSTANCE = new SourceConditionList<>(new Array<>());
    private final Array<SourceCondition<? super T>> conditions;

    private SourceConditionList(Array<SourceCondition<? super T>> conditions) {
        this.conditions = conditions;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Source> SourceConditionList<T> none() {
        return (SourceConditionList<T>) NONE_INSTANCE;
    }

    public static <T extends Source> Builder<T> builder() {
        return new Builder<>();
    }

    public Array<SourceCondition<? super T>> getConditions() {
        return new Array<>(conditions);
    }

    public static class Builder<T extends Source> {
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
