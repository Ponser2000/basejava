package com.ponser2000.basejava.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public Resume(String fullName) {
        this(getRandomUuid(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid,"uuid must not be null");
        Objects.requireNonNull(fullName,"fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    private static String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid=" + uuid +
                ", fullName=" + fullName +
                "}";
    }

}
