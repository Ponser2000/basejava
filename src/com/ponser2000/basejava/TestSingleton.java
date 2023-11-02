package com.ponser2000.basejava;

import com.ponser2000.basejava.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    public static void getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.ordinal());

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    public enum Singleton {
        INSTANCE
    }
}
