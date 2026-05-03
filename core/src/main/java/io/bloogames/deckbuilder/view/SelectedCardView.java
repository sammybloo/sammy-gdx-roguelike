package io.bloogames.deckbuilder.view;

import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.manager.CardManager;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.vfx.VFXUtils;

public class SelectedCardView extends ResizableGroup {

    private final static float WIDTH = 300;
    private final static float HEIGHT = 450;

    private CardView selectedCard;
    private CardSource selectedCardSource;

    public SelectedCardView() {
        super(WIDTH, HEIGHT);
    }

    public void setCard(CardSource cardSource) {
        selectedCard = CardManager.INSTANCE.getCard(cardSource.model());
        selectedCardSource = cardSource;
        register(selectedCard, new ResizableSettings(WIDTH, HEIGHT));
    }

    public CardView removeCard() {
        if (selectedCard != null) {
            VFXUtils.unmoor(selectedCard);
            unregister(selectedCard);
        }
        CardView temp = selectedCard;
        selectedCard = null;
        selectedCardSource = null;
        return temp;
    }

    public CardSource getSelectedCardSource() {
        return selectedCardSource;
    }
}
