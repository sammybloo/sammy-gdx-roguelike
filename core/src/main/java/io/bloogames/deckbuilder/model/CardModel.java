package io.bloogames.deckbuilder.model;

import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.data.BaseCard;
import io.bloogames.deckbuilder.effect.Effect;
import io.bloogames.deckbuilder.effect.condition.SourceConditionList;
import io.bloogames.deckbuilder.effect.context.TargetContext;
import io.bloogames.deckbuilder.effect.source.concrete.CardSource;
import io.bloogames.deckbuilder.effect.target.TargetSpec;
import io.bloogames.deckbuilder.manager.TextManager;
import io.bloogames.deckbuilder.model.aura.AuraModel;
import io.bloogames.deckbuilder.model.aura.AuraSet;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.text.Describable;
import io.bloogames.deckbuilder.text.DescriptionProperties;

public abstract class CardModel implements Describable {
    private final BaseCard base;
    private final Ownership ownership;
    private final DescriptionProperties descriptionProperties;
    private final AuraSet auraSet;
    private final ModelProperties modelProperties;
    private boolean faceup = false;

    public CardModel(BaseCard base, Ownership.Type owner) {
        this.base = base;
        this.ownership = new Ownership(owner);
        auraSet = new AuraSet(new CardSource(this), base.getAuras());
        modelProperties = new ModelProperties(base.getProperties());
        descriptionProperties = new DescriptionProperties();
        modelProperties.registerAllProperties(descriptionProperties);
    }

    public String getCardId() {
        return base.getCardId();
    }

    public String getCardName() {
        return TextManager.INSTANCE.getCardName(getCardId());
    }

    public BaseCard getBaseCard() {
        return base;
    }

    public boolean isFaceup() {
        return faceup;
    }

    public void setFaceup(boolean faceup) {
        this.faceup = faceup;
    }

    public TargetSpec getTargetSpec() {
        return base.getTargetedEffect().targetSpec();
    }

    public Effect getEffect() {
        return base.getTargetedEffect().effect();
    }

    public int getCurrentCost() {
        return base.getCost();
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public SourceConditionList<? extends CardSource> getSourceConditionList() {
        return base.getConditionList();
    }

    public Array<AuraModel> getAuras() {
        return auraSet.getAuras();
    }

    public void addAllAuras(Array<AuraModel> arr) {
        arr.addAll(auraSet.getAuras());
    }

    public ModelProperties getModelProperties() {
        return modelProperties;
    }

    public void update(TargetContext<?> context) {
        modelProperties.updateAllProperties(context);
    }

    @Override
    public String description() {
        Array<String> textParts = new Array<>();
        TextManager.INSTANCE.getCardDescription(getCardId(), descriptionProperties).ifPresent(textParts::add);

        for (AuraModel aura : auraSet.getAuras()) {
            textParts.add(aura.description());
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < textParts.size; i++) {
            if (i != 0) {
                builder.append("\n\n");
            }
            builder.append(textParts.get(i));
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return getCardName() + " card";
    }
}
