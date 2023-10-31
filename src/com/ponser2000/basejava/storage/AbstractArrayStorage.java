package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.StorageException;
import com.ponser2000.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public final void doUpdate(Object index, Resume r) {
        storage[(Integer) index] = r;
    }

    @Override
    public final void doSave(Object index, Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) index);
            size++;
        }
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    public void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected int doSize() {
        return size;
    }

    @Override
    public List<Resume> doGetAll() {
        return Arrays.stream(Arrays.copyOf(storage, size))
                .sorted(FULLNAME_COMPARATOR.thenComparing(UUID_COMPARATOR))
                .collect(Collectors.toList());
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
