package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected int doSize() {
        return storage.size();
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return storage.get(searchKey.getUuid());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Resume getSearchKey(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume searchKey, Resume r) {
        storage.replace((searchKey).getUuid(), r);
    }

    @Override
    protected void doSave(Resume searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
}
