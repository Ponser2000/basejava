package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected Resume[] doGetAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}
