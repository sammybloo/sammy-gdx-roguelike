package io.bloogames.deckbuilder.effect.target.concrete;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import io.bloogames.deckbuilder.effect.target.Target;
import io.bloogames.deckbuilder.effect.target.TargetType;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.model.TableauModel;

public class TableauTarget implements Target {

    private static final Array<TargetType> TYPES = new SnapshotArray<>(
        new TargetType[]{TargetType.TABLEAU}
    );
    private PartyModel owner;
    private TableauModel tableau;

    public TableauTarget(PartyModel owner, TableauModel tableau) {
        this.owner = owner;
        this.tableau = tableau;
    }

    @Override
    public PartyModel owner() {
        return owner;
    }

    @Override
    public Array<TargetType> types() {
        return TYPES;
    }

    public TableauModel tableau() {
        return tableau;
    }
}
