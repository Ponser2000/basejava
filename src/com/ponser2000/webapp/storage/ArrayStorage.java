package com.ponser2000.webapp.storage;
/**
 * Array based storage for Resumes
 */

import com.ponser2000.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int sizeStorage;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void update(Resume r) {
        int indexResume = getIndexResume(r.getUuid());
        if (indexResume >= 0) {
                storage[indexResume] = r;
            } else {
            System.out.println("Обновить элемент не возможно. Резюме UUID: " + r.getUuid() + " отсутствует в базе");
        }
    }

    public void save(Resume r) {
        if (sizeStorage >= storage.length) {
            System.out.println("Хранилище заполнено полностью. Сохранить резюме UUID: " + r.getUuid() +  "не возможно.");
        } else if (getIndexResume(r.getUuid()) < 0) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Сохранить элемент не возможно. Резюме UUID: " + r.getUuid() + " уже есть в базе");
        }
    }

    public Resume get(String uuid) {
        int indexResume = getIndexResume(uuid);
        if (indexResume >= 0) {
            return storage[indexResume];
        } else {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        }
        return null;
    }

    public void delete(String uuid) {
        int indexResume = getIndexResume(uuid);
        if (indexResume >= 0) {
            storage[indexResume] = storage[sizeStorage - 1];
            storage[sizeStorage - 1] = null;
            sizeStorage--;
        } else {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    public int getIndexResume(String uuid) {
        int index = -1;
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }
}