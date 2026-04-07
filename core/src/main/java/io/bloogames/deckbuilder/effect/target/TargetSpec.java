package io.bloogames.deckbuilder.effect.target;

import com.badlogic.gdx.utils.ObjectMap;

public final class TargetSpec {
    private final ObjectMap<TargetType, Boolean> validTypes = new ObjectMap<>();
    private final TargetOwnerType ownerType;

    public TargetSpec(TargetOwnerType ownerType, TargetType... validTypes) {
        this.ownerType = ownerType;
        for (TargetType type : validTypes) {
            this.validTypes.put(type, true);
        }
    }

    public boolean allows(TargetType type, TargetOwnerType ownerType) {
        boolean typeAllowed = validTypes.containsKey(type);
        boolean ownerAllowed = this.ownerType == TargetOwnerType.ANY || this.ownerType == ownerType;
        return typeAllowed && ownerAllowed;
    }

    public TargetOwnerType ownerType() {
        return ownerType;
    }
}
