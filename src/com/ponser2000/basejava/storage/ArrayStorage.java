package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertElement(Resume r, int index) {
        STORAGE[size] = r;
    }

    @Override
    protected void fillDeletedElement(int index) {
        STORAGE[index] = STORAGE[size - 1];
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}