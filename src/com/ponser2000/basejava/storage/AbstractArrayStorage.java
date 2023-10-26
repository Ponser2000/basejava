package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.StorageException;
import com.ponser2000.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    protected void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void updateElement(int index,Resume r) {
        storage[index] = r;
    }

    @Override
    public final void saveElement(int index, Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected Resume getElement(String uuid) {
        int index = checkNotExistElement(uuid);
        return storage[index];
    }

    @Override
    public void deleteElement(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int amountElements() {
        return size;
    }

    @Override
    public Resume[] allElements() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
