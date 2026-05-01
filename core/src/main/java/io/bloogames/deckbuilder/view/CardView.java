package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.manager.FontManager;
import io.bloogames.deckbuilder.model.CardModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.scene2d.UpdatingLabel;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.target.Targetable;
import io.bloogames.deckbuilder.ui.target.TargetingVisualState;

public abstract class CardView extends ResizableGroup implements View, Targetable {

    public static final float WIDTH = 360f;
    public static final float HEIGHT = 540f;

    private final TargetingVisualState targetingVisualState = new TargetingVisualState();
    private final CardModel cardModel;
    protected Image cardBack;
    protected Image frame;
    protected Image art;
    protected Image manaSymbol;
    protected Label nameLabel;
    protected UpdatingLabel manaLabel;
    protected ResizableGroup frontFace;

    public CardView(CardModel cardModel, String frameReference, String artReference) {
        super(WIDTH, HEIGHT);
        this.cardModel = cardModel;
        setOrigin(Align.center);

        this.frontFace = new ResizableGroup(WIDTH, HEIGHT);
        frontFace.setTouchable(Touchable.disabled);

        this.art = new Image(AssetManager.INSTANCE.findRegion(artReference));

        this.frame = new Image(AssetManager.INSTANCE.findRegion(frameReference));

        this.manaSymbol = new Image(AssetManager.INSTANCE.findRegion("cardmana"));

        nameLabel = new Label(cardModel.getCardName(),
            new Label.LabelStyle(FontManager.INSTANCE.getCardNameFont(), null));
        nameLabel.setAlignment(Align.center, Align.left);

        manaLabel = new UpdatingLabel(60, 60, cardModel.getCurrentCost() + "",
            FontManager.INSTANCE.getCardManaCostFont());
        manaLabel.getLabel().setAlignment(Align.center, Align.center);

        frontFace.register(art, new ResizableSettings(WIDTH * 0.948f, WIDTH * 0.948f).offset(WIDTH * 0.025f, HEIGHT * 0.284f));
        frontFace.register(frame, new ResizableSettings(WIDTH, HEIGHT, Align.center));
        frontFace.register(manaSymbol, new ResizableSettings(60, 60, Align.topLeft).offset(-10f, -10f));
        frontFace.register(manaLabel, new ResizableSettings(manaLabel.getTargetWidth(), manaLabel.getTargetHeight(), Align.topLeft)
            .offset(-10f, -10f).keepColour());
        frontFace.register(nameLabel, new ResizableSettings(WIDTH - 60f, 30, Align.top).offset(30f, 9f));

        cardBack = new Image(AssetManager.INSTANCE.findRegion("cardback"));
        register(frontFace, new ResizableSettings(WIDTH, HEIGHT, Align.center));
        register(cardBack, new ResizableSettings(WIDTH, HEIGHT, Align.center));
        setTouchable(Touchable.enabled);

        addTint(targetingVisualState().getTint());
    }

    public CardModel getModel() {
        return cardModel;
    }

    public void setFacing(boolean isFaceUp) {
        frontFace.setVisible(isFaceUp);
        cardBack.setVisible(!isFaceUp);
    }

    public boolean isFaceup() {
        return frontFace.isVisible();
    }

    public ResizableGroup getFrontFace() {
        return frontFace;
    }

    public Image getCardBack() {
        return cardBack;
    }

    @Override
    public TargetingVisualState targetingVisualState() {
        return targetingVisualState;
    }

    @Override
    public Actor actor() {
        return this;
    }

    @Override
    public void applyHighlight() {
        targetingVisualState().updateTint();
    }

    @Override
    public void sync() {
        setFacing(cardModel.isFaceup());
        nameLabel.setText(cardModel.getCardName());
        manaLabel.setText(cardModel.getCurrentCost() + "");
        manaLabel.setColourByComparisonInverted(cardModel.getBaseCard().getCost(), cardModel.getCurrentCost());
    }
}
