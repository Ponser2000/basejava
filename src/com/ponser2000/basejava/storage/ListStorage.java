package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void doClear() {
        storage.clear();
    }

    @Override
    public void updateElement(int index,Resume r) {
        storage.set(index, r);
    }

    @Override
    public void saveElement(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getElement(String uuid) {
        int index = checkNotExistElement(uuid);
        return storage.get(index);
    }

    @Override
    public void deleteElement(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume[] allElements() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected int amountElements() {
        return storage.size();
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
