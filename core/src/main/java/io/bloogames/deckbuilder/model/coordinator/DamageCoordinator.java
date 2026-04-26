package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.damage.Damage;
import io.bloogames.deckbuilder.model.Aura;
import io.bloogames.deckbuilder.model.GameModel;

public class DamageCoordinator {
    public void applyModifiers(GameModel game, Damage damage) {
        damage.clearModifiers();

        // TO-DO  work out where to put this so it needs to be done as little as possible
        Array<Aura> auras = game.getAllAuras();
    }
}
