package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.model.ownership.Ownership;
import io.bloogames.deckbuilder.ui.View;
import io.bloogames.deckbuilder.ui.color.Tint;
import io.bloogames.deckbuilder.ui.scene2d.IconGrid;
import io.bloogames.deckbuilder.ui.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.ui.scene2d.ResizableSettings;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static io.bloogames.deckbuilder.manager.CustomActions.tint;

public class ManaView extends ResizableGroup implements View {

    public final static int WIDTH = 80;
    public final static int HEIGHT = 200;
    private final static float FULL = new Color(0.3f, 0.3f, 1f, 0.8f).toFloatBits();
    private final static float EMPTY = new Color(0.3f, 0.3f, 0.3f, 0.3f).toFloatBits();
    private final Array<ManaSymbol> manaSymbols;
    private final IconGrid iconGrid;
    private final LeaderModel leader;

    public ManaView(LeaderModel leader) {
        super(WIDTH, HEIGHT);
        this.leader = leader;
        iconGrid = new IconGrid(IconGrid.PrimaryDirection.VERTICAL, IconGrid.HorizontalAlign.LEFT_TO_RIGHT,
            leader.getOwnership().getCurrentOwner() == Ownership.Type.PLAYER ? IconGrid.VerticalAlign.BOTTOM_TO_TOP : IconGrid.VerticalAlign.TOP_TO_BOTTOM, 2, 0);
        manaSymbols = new Array<>(10);
        for (int i = 0; i < 10; i++) {
            manaSymbols.add(new ManaSymbol(i));
            iconGrid.addActor(manaSymbols.get(i));
        }
        register(iconGrid, new ResizableSettings(WIDTH, HEIGHT));
        sync();
    }

    public void sync() {
        for (int i = 0; i < manaSymbols.size; i++) {
            if (i < leader.getCurrentMana()) {
                manaSymbols.get(i).setFull();
            } else if (i < leader.getMaxMana()) {
                manaSymbols.get(i).setEmpty();
            } else {
                manaSymbols.get(i).setInvisible();
            }
        }
    }

    private class ManaSymbol extends ResizableGroup {
        private final Tint tint = new Tint();
        private final Image image;
        private ManaState state = ManaState.INVISIBLE;

        public ManaSymbol(int index) {
            super(50, 50);
            addTint(tint);
            image = new Image(AssetManager.INSTANCE.findRegion("mana"));
            register(image, new ResizableSettings(50, 50));
            setVisible(false);

            image.setRotation(index * 45);
        }

        public void setFull() {
            if (state != ManaState.FULL) {
                setVisible(true);
                image.addAction(
                    parallel(
                        fadeIn(0.2f),
                        tint(tint, FULL, 0.2f),
                        sequence(
                            Actions.rotateBy(15, 0.05f),
                            Actions.rotateBy(-30f, 0.1f),
                            Actions.rotateBy(15f, 0.05f)
                        )
                    )
                );
                this.state = ManaState.FULL;
            }
        }

        public void setEmpty() {
            if (state != ManaState.EMPTY) {
                setVisible(true);
                image.addAction(
                    parallel(
                        fadeIn(0.2f),
                        tint(tint, EMPTY, 0.2f),
                        sequence(
                            Actions.rotateBy(15, 0.05f),
                            Actions.rotateBy(-30f, 0.1f),
                            Actions.rotateBy(15f, 0.05f)
                        )
                    )
                );
                this.state = ManaState.EMPTY;
            }
        }

        public void setInvisible() {
            if (state != ManaState.INVISIBLE) {
                addAction(
                    sequence(
                        fadeOut(0.2f),
                        hide()
                    )
                );
                this.state = ManaState.INVISIBLE;
            }
        }

        private enum ManaState {EMPTY, FULL, INVISIBLE}
    }
}
