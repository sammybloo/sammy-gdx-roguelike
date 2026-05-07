package io.bloogames.deckbuilder.libgdx;

import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

public class ImmutableArray<T> extends Array<T> {
    public ImmutableArray() {
        super();
    }

    public ImmutableArray(Array<? extends T> array) {
        super(array);
    }

    @Override
    public void add(T value) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void add(T value1, T value2) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void add(T value1, T value2, T value3) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void add(T value1, T value2, T value3, T value4) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void addAll(Array<? extends T> array) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void addAll(Array<? extends T> array, int start, int count) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void addAll(T... array) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void addAll(T[] array, int start, int count) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void set(int index, T value) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void insert(int index, T value) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void insertRange(int index, int count) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void swap(int first, int second) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public boolean replaceFirst(T value, boolean identity, T replacement) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public int replaceAll(T value, boolean identity, T replacement) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void shuffle() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void reverse() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void sort() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    protected T[] resize(int newSize) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public T[] setSize(int newSize) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public T[] ensureCapacity(int additionalCapacity) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public T[] shrink() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public T pop() {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public boolean removeAll(Array<? extends T> array, boolean identity) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public void removeRange(int start, int end) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public T removeIndex(int index) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }

    @Override
    public boolean removeValue(T value, boolean identity) {
        throw new UnsupportedOperationException("Cannot modify immutable array");
    }
}

