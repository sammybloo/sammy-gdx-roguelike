package io.bloogames.deckbuilder.model.coordinator;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.event.GameEvent;
import io.bloogames.deckbuilder.model.damage.Damage;
import io.bloogames.deckbuilder.model.damage.DamagePreventer;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.Source;
import io.bloogames.deckbuilder.effect.target.concrete.DamageableTarget;
import io.bloogames.deckbuilder.model.GameModel;
import io.bloogames.deckbuilder.model.aura.Aura;

import java.util.Optional;

public class DamageCoordinator {
    private GameModel game;

    public DamageCoordinator(GameModel game) {
        this.game = game;
    }

    public void damage(Source source, DamageableTarget target, Damage damage) {
        damage.clear();

        Array<Aura> auras = game.getAllAuras();

        TargetContext<DamageableTarget> context = new TargetContext<DamageableTarget>(game, source, target);
        for (Aura aura : auras) {
            aura.beforeDamage(context, damage);
        }

        Optional<DamagePreventer> preventer = damage.getPreventer();

        if (preventer.isPresent()) {
            preventer.get().onPrevent(context, damage);
            return;
        }

        target.damageable().damage(damage.getAmount());

        game.dispatch(new GameEvent.DamageDealtEvent(
            source, target.damageable(), damage.getAmount()));
    }
}
