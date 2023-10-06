package com.ponser2000.webapp.storage;

import com.ponser2000.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
            return null;
        }
        return STORAGE[index];
    }

    protected abstract int getIndex(String uuid);
}
