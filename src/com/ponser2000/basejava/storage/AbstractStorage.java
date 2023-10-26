package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.ExistStorageException;
import com.ponser2000.basejava.exception.NotExistStorageException;
import com.ponser2000.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int doSize();

    protected abstract Resume doGet(Object searchKey);

    protected abstract Resume[] doGetAll();

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doClear();

    @Override
    public int size() {
        return doSize();
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getNotExistElement(uuid);
        return doGet(searchKey);
    }

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getNotExistElement(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getNotExistElement(uuid);
        doDelete(searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getExistElement(r.getUuid());
        doSave(searchKey, r);
    }

    protected Object getExistElement(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if ((Integer) searchKey >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected Object getNotExistElement(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if ((Integer) searchKey < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

}
