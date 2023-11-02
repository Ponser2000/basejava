package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void doClear() {
        storage.clear();
    }

    @Override
    public void doUpdate(Integer index, Resume r) {
        storage.set(index, r);
    }

    @Override
    public void doSave(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage.get(index);
    }

    @Override
    public void doDelete(Integer index) {
        storage.remove(index.intValue());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage);
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
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
