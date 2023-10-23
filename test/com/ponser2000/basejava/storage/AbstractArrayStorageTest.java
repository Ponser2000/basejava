package com.ponser2000.basejava.storage;

import com.ponser2000.basejava.exception.ExistStorageException;
import com.ponser2000.basejava.exception.NotExistStorageException;
import com.ponser2000.basejava.exception.StorageException;
import com.ponser2000.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        Assert.assertEquals(storage.get(UUID_2), r);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(storage.get(RESUME_4.getUuid()), RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_3);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = 4; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
            storage.save(new Resume());
        }
        storage.save(RESUME_4);
    }

    @Test
    public void get() {
        Resume r = storage.get(RESUME_1.getUuid());
        Assert.assertEquals(r, RESUME_1);

        r = storage.get(RESUME_2.getUuid());
        Assert.assertEquals(r, RESUME_2);

        r = storage.get(RESUME_3.getUuid());
        Assert.assertEquals(r, RESUME_3);

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(RESUME_4.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] r = storage.getAll();
        Assert.assertEquals(RESUME_1, r[0]);
        Assert.assertEquals(RESUME_2, r[1]);
        Assert.assertEquals(RESUME_3, r[2]);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(RESUME_4.getUuid());
    }

}