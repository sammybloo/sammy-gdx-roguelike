package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.model.aura.Aura;
import io.bloogames.deckbuilder.model.ownership.Ownership;

public class PartyModel {
    private final LeaderModel leader;
    private final DeckModel fullDeck;
    private final Array<TrinketModel> trinkets;

    public PartyModel(LeaderModel leader) {
        this.leader = leader;
        this.fullDeck = new DeckModel(getOwnership().getCurrentOwner());
        this.trinkets = new Array<>();
    }

    public LeaderModel getLeader() {
        return leader;
    }

    public DeckModel getFullDeck() {
        return fullDeck;
    }

    public Ownership getOwnership() {
        return leader.getOwnership();
    }

    public void addAllAuras(Array<Aura> arr) {
        getLeader().addAllAuras(arr);
        for (TrinketModel trinket : trinkets) {
            trinket.addAllAuras(arr);
        }
    }
}
