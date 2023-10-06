package com.ponser2000.webapp.storage;
/**
 * Array based storage for Resumes
 */

import com.ponser2000.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

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

    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Сохранить элемент не возможно. Резюме UUID: " + r.getUuid() + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Хранилище заполнено полностью. Сохранить резюме UUID: " + r.getUuid() + "не возможно.");
        } else {
            STORAGE[size] = r;
            size++;
        }
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        } else {
            STORAGE[index] = STORAGE[size - 1];
            STORAGE[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
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