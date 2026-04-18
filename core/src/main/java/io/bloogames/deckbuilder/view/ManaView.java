package io.bloogames.deckbuilder.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import io.bloogames.deckbuilder.manager.AssetManager;
import io.bloogames.deckbuilder.model.LeaderModel;
import io.bloogames.deckbuilder.scene2d.ResizableGroup;
import io.bloogames.deckbuilder.scene2d.ResizableSettings;
import io.bloogames.deckbuilder.ui.View;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ManaView extends ResizableGroup implements View {

    public final static int WIDTH = 80;
    public final static int HEIGHT = 200;
    private final static Color FULL = new Color(0.6f, 0.6f, 1f, 0.8f);
    private final static Color EMPTY = new Color(0.6f, 0.6f, 0.6f, 0.3f);
    private final Array<ManaSymbol> manaSymbols;
    private final LeaderModel leader;

    public ManaView(LeaderModel leader) {
        super(WIDTH, HEIGHT);
        this.leader = leader;
        manaSymbols = new Array<>(10);
        for (int i = 0; i < 10; i++) {
            manaSymbols.add(new ManaSymbol(AssetManager.INSTANCE.getSprite("mana")));
            register(manaSymbols.get(i), new ResizableSettings(WIDTH / 2, HEIGHT / 5)
                .offset(i > 4 ? WIDTH / 2 : 0, HEIGHT / 5 * (i % 5)).keepAspect());
            manaSymbols.get(i).setRotation(i * 45);
        }
        sync();
    }

    public void sync() {
        for (int i = 0; i < manaSymbols.size; i++) {
            if (i < leader.getCurrentMana()) {
                manaSymbols.get(i).setFull();
            } else if (i < leader.getMaxMana()) {
                manaSymbols.get(i).setEmpty();
            }
            else {
                manaSymbols.get(i).setInvisible();
            }
        }
    }

    private class ManaSymbol extends Image {
        private enum ManaState {EMPTY, FULL, INVISIBLE}

        private ManaState state = ManaState.INVISIBLE;

        public ManaSymbol(TextureRegion textureRegion) {
            super(textureRegion);
            setVisible(false);
        }

        public void setFull() {
            if (state != ManaState.FULL) {
                setVisible(true);
                addAction(
                    parallel(
                        fadeIn(0.2f),
                        color(FULL, 0.2f),
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
                addAction(
                    parallel(
                        fadeIn(0.2f),
                        color(EMPTY, 0.2f),
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
    }
}
