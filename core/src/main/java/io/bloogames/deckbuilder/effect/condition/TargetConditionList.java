package io.bloogames.deckbuilder.effect.condition;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;

public class TargetConditionList {
    private static final TargetConditionList NONE_INSTANCE = new TargetConditionList(new ObjectMap<>());
    private final ObjectMap<TargetType, Array<TargetCondition<? extends Target>>> conditionsByType;

    private TargetConditionList(ObjectMap<TargetType, Array<TargetCondition<? extends Target>>> conditionsByType) {
        this.conditionsByType = conditionsByType;
    }

    public static TargetConditionList none() {
        return NONE_INSTANCE;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Array<TargetCondition<? extends Target>> getConditions(TargetType targetType) {
        Array<TargetCondition<? extends Target>> conditions = conditionsByType.get(targetType);
        return conditions == null ? new Array<>() : new Array<>(conditions);
    }

    public static class Builder {
        private final ObjectMap<TargetType, Array<TargetCondition<? extends Target>>> conditionsByType = new ObjectMap<>();

        public Builder add(TargetType targetType, TargetCondition<? extends Target> condition) {
            Array<TargetCondition<? extends Target>> conditions = conditionsByType.get(targetType);
            if (conditions == null) {
                conditions = new Array<>();
                conditionsByType.put(targetType, conditions);
            }
            conditions.add(condition);
            return this;
        }

        public TargetConditionList build() {
            ObjectMap<TargetType, Array<TargetCondition<? extends Target>>> copy = new ObjectMap<>();
            for (ObjectMap.Entry<TargetType, Array<TargetCondition<? extends Target>>> entry : conditionsByType) {
                copy.put(entry.key, new Array<>(entry.value));
            }
            return new TargetConditionList(copy);
        }
    }
}
