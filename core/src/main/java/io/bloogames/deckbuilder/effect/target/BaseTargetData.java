package io.bloogames.deckbuilder.effect.target;

public class BaseTargetData {
    private TargetType targetType;
    private TargetOwnerType targetOwnerType;

    public BaseTargetData(TargetType targetType, TargetOwnerType targetOwnerType) {
        this.targetType = targetType;
        this.targetOwnerType = targetOwnerType;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public TargetOwnerType getTargetOwnerType() {
        return targetOwnerType;
    }
}
