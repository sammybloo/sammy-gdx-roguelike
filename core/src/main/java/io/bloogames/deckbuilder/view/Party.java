package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import io.bloogames.deckbuilder.model.PartyModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;

public abstract class Party extends ResizableGroup {

    private PartyModel model;
    private Leader leader;
    private Hand hand;
    private Tableau tableau;

    public static final float WIDTH = 1920;
    public static final float HEIGHT = 540;

    public Party(PartyModel model) {
        super(WIDTH, HEIGHT);
        this.model = model;
    }

    public PartyModel getModel() {
        return model;
    }

    public void setModel(PartyModel model) {
        this.model = model;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Tableau getTableau() {
        return tableau;
    }

    public void setTableau(Tableau tableau) {
        this.tableau = tableau;
    }
}
