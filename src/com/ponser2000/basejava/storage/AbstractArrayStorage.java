package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Обновить элемент не возможно. Резюме UUID: " + r.getUuid() + " отсутствует в базе");
        } else {
            STORAGE[index] = r;
        }
    }

    protected abstract int getIndex(String uuid);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
            return null;
        }
        return STORAGE[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

}
