package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.TableauModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class TableauTarget implements Target {

    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.TABLEAU}
    );
    private final TableauModel tableau;

    public TableauTarget(PartyModel owner, TableauModel tableau) {
        this.tableau = tableau;
    }

    @Override
    public Ownership.Type owner() {
        return tableau.getOwnership().getCurrentOwner();
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public TableauModel tableau() {
        return tableau;
    }
}
