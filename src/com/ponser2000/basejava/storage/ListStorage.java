package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void doClear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Object index, Resume r) {
        storage.set((Integer) index, r);
    }

    @Override
    public void doSave(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    public void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    public Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
