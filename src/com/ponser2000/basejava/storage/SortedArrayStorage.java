package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Сохранить элемент не возможно. Резюме UUID: " + r.getUuid() + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Хранилище заполнено полностью. Сохранить резюме UUID: " + r.getUuid() + "не возможно.");
        } else {
            int index = size;
            for (int i = 0; i < size; i++) {
                if (STORAGE[i].getUuid().compareToIgnoreCase(r.getUuid()) > 0) {
                    index = i;
                    break;
                }
            }
            for (int i = size; i > index; i--) {
                STORAGE[i] = STORAGE[i - 1];
            }
            STORAGE[index] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме UUID: " + uuid + " отсутствует в базе");
        } else {
            for (int i = index; i < size - 1; i++) {
                STORAGE[i] = STORAGE[i + 1];
            }
            STORAGE[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int index = Arrays.binarySearch(STORAGE, 0, size, searchKey);
        return (index < 0) ? -1 : index;
    }
}
