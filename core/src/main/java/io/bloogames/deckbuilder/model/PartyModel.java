package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;

public class PartyModel {
    private final LeaderModel leader;
    private final DeckModel fullDeck;
    private Array<TrinketModel> trinkets;

    public PartyModel(LeaderModel leader) {
        this.leader = leader;
        this.fullDeck = new DeckModel();
        this.trinkets = new Array<>();
    }

    public LeaderModel getLeader() {
        return leader;
    }

    public DeckModel getFullDeck() {
        return fullDeck;
    }

    public void addAllAuras(Array<Aura> arr) {
        getLeader().addAllAuras(arr);
        for (TrinketModel trinket : trinkets) {
            trinket.addAllAuras(arr);
        }
    }
}
