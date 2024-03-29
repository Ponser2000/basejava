package com.ponser2000.basejava;

import com.ponser2000.basejava.model.Resume;
import com.ponser2000.basejava.storage.ListStorage;
import com.ponser2000.basejava.storage.Storage;

/**
 * Test for your com.ponser2000.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {

    //   private final static Storage ARRAY_STORAGE = new SortedArrayStorage();
    private final static Storage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid4");
        final Resume r2 = new Resume("uuid2");
        final Resume r3 = new Resume("uuid3");
        final Resume r4 = new Resume("uuid1");
        final Resume r5 = new Resume("uuid5");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);

        printAll();

        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.update(r4);
        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r5.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
