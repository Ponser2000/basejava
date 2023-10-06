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
        if (index == -1) {
            System.out.println("Обновить элемент не возможно. Резюме UUID: " + r.getUuid() + " отсутствует в базе");
        } else {
            STORAGE[index] = r;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Сохранить элемент не возможно. Резюме UUID: " + r.getUuid() + " уже есть в базе");
        } else if (sizeStorage == STORAGE.length) {
            System.out.println("Хранилище заполнено полностью. Сохранить резюме UUID: " + r.getUuid() + "не возможно.");
        } else {
            STORAGE[sizeStorage] = r;
            sizeStorage++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
            return null;
        }
        return STORAGE[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        } else {
            STORAGE[index] = STORAGE[sizeStorage - 1];
            STORAGE[sizeStorage - 1] = null;
            sizeStorage--;
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

    private int getIndex(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (STORAGE[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}