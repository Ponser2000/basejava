/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeStorage = 0;

    void clear() {
        for (int i = 0; i < size(); i++)
            storage[i] = null;
        sizeStorage = 0;
    }

    void save(Resume r) {
        if (size() < storage.length) {
            storage[size()] = r;
            sizeStorage++;
        } else
            System.out.println("Хранилище заполнено полностью. Сохранить элемент не возможно.");
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size() - 1];
                break;
            }
        }
        storage[size() - 1] = null;
        sizeStorage--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size()];
        if (size() >= 0) {
            System.arraycopy(storage, 0, allResume, 0, size());
        }
        return allResume;
    }

    int size() {
        return sizeStorage;
    }
}