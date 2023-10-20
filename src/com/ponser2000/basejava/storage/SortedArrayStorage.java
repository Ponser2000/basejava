package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (check(r) != -1) {
            int index = size;
            for (int i = 0; i < size; i++) {
                if (STORAGE[i].getUuid().compareToIgnoreCase(r.getUuid()) > 0) {
                    index = i;
                    break;
                }
            }
            System.arraycopy(STORAGE,index,STORAGE,index+1,size-index);
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
            System.arraycopy(STORAGE,index+1,STORAGE,index,size-index-1);
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
