package com.ponser2000.basejava.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

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

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid=" + uuid +
                ", fullName=" + fullName +
                "}";
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
