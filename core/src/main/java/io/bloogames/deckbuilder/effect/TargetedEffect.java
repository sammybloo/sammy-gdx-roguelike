package io.bloogames.deckbuilder.effect;

import io.bloogames.deckbuilder.effect.target.TargetSpec;

public record TargetedEffect(TargetSpec targetSpec, Effect effect) {
}
