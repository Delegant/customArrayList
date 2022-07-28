package com.aston.customarraylist.service.impl;

import com.aston.customarraylist.service.CustomArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class CustomArrayListImpl<T> implements CustomArrayList<T> {

    private final int STARTER_VOLUME = 10;
    private Object[] entityArray;
    private int size;


    public CustomArrayListImpl() {
        entityArray = new Object[STARTER_VOLUME];
    }

    public CustomArrayListImpl(int initSize) {
        if (initSize > 0) {
            entityArray = new Object[initSize];
        } else throw new IllegalArgumentException();
    }

    private void increaseVolume() {
        Object[] newEntityArray = new Object[entityArray.length * 2];
        for (int i = 0; i < entityArray.length; i++) {
            newEntityArray[i] = entityArray[i];
        }
        entityArray = newEntityArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > entityArray.length - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T add(T item) {
        if (size == entityArray.length - 1) {
            increaseVolume();
        }
        entityArray[size] = item;
        size++;
        return item;
    }

    @Override
    public void add(int index, T item) {
        checkIndex(index);
        if (size == entityArray.length - 1) {
            increaseVolume();
        }
        for (int i = 0; i <= size - index; i++) {
            entityArray[size - i + 1] = entityArray[size - i];
        }
        entityArray[index] = item;
        size++;
    }


    @Override
    public boolean addAll(Collection<? extends T> items) {
        if (items == null) {
            throw new NullPointerException();
        }
        for (T item : items) {
            add(size, item);
        }
        return true;
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);
        entityArray[index] = item;
        return item;
    }

    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T item = get(index);
        for (int i = index; i < size - 1; i++) {
            entityArray[i] = entityArray[i + 1];
        }
        size--;
        return item;
    }

    @Override
    public int replace(T oldValue, T newValue) {
        int number = 0;
        while (indexOf(oldValue) != -1) {
            int index = indexOf(oldValue);
            entityArray[index] = newValue;
            number++;
        }
        return number;
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < entityArray.length; i++) {
            if (Objects.equals(entityArray[i], item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) entityArray[index];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        entityArray = new String[STARTER_VOLUME];
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != CustomArrayListImpl.class) return false;
        CustomArrayListImpl<?> that = (CustomArrayListImpl<?>) o;
        if (that.size() != size) return false;
        for (int i = size - 1; i >= 0; i--) {
            if (!Objects.equals(that.get(i), entityArray[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(entityArray);
        return result;
    }
}
