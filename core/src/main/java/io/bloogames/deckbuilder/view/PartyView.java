package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import io.bloogames.deckbuilder.model.BattlePartyModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.View;

public abstract class PartyView extends ResizableGroup implements View {

    public static final float WIDTH = 1920;
    public static final float HEIGHT = 540;
    private BattlePartyModel model;
    private LeaderView leader;
    private HandView hand;
    private TableauView tableau;
    private ManaView manaView;
    private DeckView deck;
    private DiscardPileView discardPile;

    public PartyView(BattlePartyModel model) {
        super(WIDTH, HEIGHT);
        setTouchable(Touchable.childrenOnly);
        this.model = model;
    }

    public BattlePartyModel getModel() {
        return model;
    }

    public void setModel(BattlePartyModel model) {
        this.model = model;
    }

    public LeaderView getLeader() {
        return leader;
    }

    public void setLeader(LeaderView leader) {
        this.leader = leader;
    }

    public HandView getHand() {
        return hand;
    }

    public void setHand(HandView hand) {
        this.hand = hand;
    }

    public TableauView getTableau() {
        return tableau;
    }

    public void setTableau(TableauView tableau) {
        this.tableau = tableau;
    }

    public ManaView getManaView() {
        return manaView;
    }

    public void setManaView(ManaView manaView) {
        this.manaView = manaView;
    }

    public DeckView getDeck() {
        return deck;
    }

    public void setDeck(DeckView deckView) {
        this.deck = deckView;
    }

    public DiscardPileView getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardPileView discardPile) {
        this.discardPile = discardPile;
    }

    @Override
    public void sync() {
        leader.sync();
        hand.sync();
        tableau.sync();
        deck.sync();
        discardPile.sync();
    }
}
