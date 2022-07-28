package com.aston.customarraylist.service;

import java.util.Collection;

public interface CustomArrayList<T> {


    T add(T item);

    void add(int index, T item);

    boolean addAll(Collection<? extends T> item);

    T set(int index, T item);

    boolean remove(T item);

    T remove(int index);

    int replace(T oldValue, T newValue);

    int indexOf(T item);

    T get(int index);

    int size();

    boolean isEmpty();

    void clear();

}
