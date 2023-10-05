package com.ponser2000.webapp.storage;
/**
 * Array based storage for Resumes
 */

import com.ponser2000.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private final int STORAGE_LIMIT = 10000;
    private final Resume[] STORAGE = new Resume[STORAGE_LIMIT];
    private int sizeStorage;

    public void clear() {
        Arrays.fill(STORAGE, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            STORAGE[index] = r;
        } else {
            System.out.println("Обновить элемент не возможно. Резюме UUID: " + r.getUuid() + " отсутствует в базе");
        }
    }

    public void save(Resume r) {
        if (sizeStorage >= STORAGE.length) {
            System.out.println("Хранилище заполнено полностью. Сохранить резюме UUID: " + r.getUuid() + "не возможно.");
        } else if (getIndex(r.getUuid()) < 0) {
            STORAGE[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Сохранить элемент не возможно. Резюме UUID: " + r.getUuid() + " уже есть в базе");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return STORAGE[index];
        } else {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            STORAGE[index] = STORAGE[sizeStorage - 1];
            STORAGE[sizeStorage - 1] = null;
            sizeStorage--;
        } else {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    public int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < sizeStorage; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }
}