package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.ExistStorageException;
import com.ponser2000.basejava.exception.NotExistStorageException;
import com.ponser2000.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Comparator<Resume> STORAGE_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract int doSize();

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(SK searchKey, Resume r);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doClear();

    protected abstract boolean isExist(SK searchKey);

    @Override
    public int size() {
        return doSize();
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getExistElement(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = doCopyAll();
        result.sort(STORAGE_COMPARATOR);
        return result;
    }

    @Override
    public void update(Resume r) {
        SK searchKey = getExistElement(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getExistElement(uuid);
        doDelete(searchKey);
    }

    @Override
    public void save(Resume r) {
        SK searchKey = getNotExistElement(r.getUuid());
        doSave(searchKey, r);
    }

    protected SK getNotExistElement(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    protected SK getExistElement(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

}
