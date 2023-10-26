package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.ExistStorageException;
import com.ponser2000.basejava.exception.NotExistStorageException;
import com.ponser2000.basejava.model.Resume;

public abstract class AbstractStorage implements Storage{
    protected abstract int amountElements();

    protected abstract Resume getElement(String uuid);

    protected abstract Resume[] allElements();

    protected abstract int getIndex(String uuid);

    protected abstract void updateElement(int index, Resume r);
    protected abstract void saveElement(int index, Resume r);

    protected abstract void deleteElement(int index);

    protected abstract void doClear();

    @Override
    public int size() {
        return amountElements();
    }

    @Override
    public Resume get(String uuid) {
        return getElement(uuid);
    }

    @Override
    public Resume[] getAll() {
        return allElements();
    }

    @Override
    public void update(Resume r) {
        int index = checkNotExistElement(r.getUuid());
        updateElement(index, r);
    }

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void delete(String uuid) {
        int index = checkNotExistElement(uuid);
        deleteElement(index);
    }

    @Override
    public void save(Resume r) {
        int index = checkExistElement(r.getUuid());
        saveElement(index, r);
    }

    protected int checkExistElement(String uuid){
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            return index;
        }
    }

    protected int checkNotExistElement(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return index;
        }
    }

}
