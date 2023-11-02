package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Object getSearchKey(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace(((Resume) searchKey).getUuid(), r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
