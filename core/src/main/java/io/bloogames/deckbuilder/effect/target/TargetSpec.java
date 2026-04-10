package io.bloogames.deckbuilder.effect.target;

import com.badlogic.gdx.utils.ObjectMap;
import io.bloogames.deckbuilder.effect.condition.TargetConditionList;

public final class TargetSpec {
    private final ObjectMap<TargetType, Boolean> validTypes = new ObjectMap<>();
    private final TargetOwnerType ownerType;
    private final TargetConditionList conditionList;

    public TargetSpec(TargetOwnerType ownerType, TargetConditionList conditionList, TargetType... validTypes) {
        this.ownerType = ownerType;
        this.conditionList = conditionList;
        for (TargetType type : validTypes) {
            this.validTypes.put(type, true);
        }
    }

    public boolean allows(TargetType type) {
        return validTypes.containsKey(type);
    }

    public TargetOwnerType ownerType() {
        return ownerType;
    }

    public TargetConditionList getConditionList() {
        return conditionList;
    }
}
