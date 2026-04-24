package io.bloogames.deckbuilder.manager;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

public enum SeedManager {
    INSTANCE;

    String seed;
    private Random worldRandom;
    private Random battleRandom;

    public void setSeed(String seed) {
        this.seed = seed;
        worldRandom = new Random(seed.hashCode());
        battleRandom = new Random(seed.hashCode());
    }

    public void shuffle(Array<?> array) {
        // Taken from GDX Array
        Object[] items = array.items;
        for (int i = array.size - 1; i >= 0; i--) {
            int ii = battleRandom.nextInt(i + 1);
            Object temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
    }
}
