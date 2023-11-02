package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected Resume doGet(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String searchKey, Resume r) {
        storage.replace(searchKey, r);
    }

    @Override
    protected void doSave(String searchKey, Resume r) {
        storage.put(searchKey, r);
    }

    @Override
    protected void doDelete(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }
}
