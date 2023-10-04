/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    int sizeStorage;

    void clear() {
        for (int i = 0; i < sizeStorage; i++) {
            storage[i] = null;
        }
        sizeStorage = 0;
    }

    void save(Resume r) {
        if (sizeStorage < storage.length) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Хранилище заполнено полностью. Сохранить элемент не возможно.");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[sizeStorage - 1];
                storage[sizeStorage - 1] = null;
                sizeStorage--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[sizeStorage];
        for (int i = 0; i < sizeStorage; i++) {
            allResume[i] = new Resume();
            allResume[i].uuid = storage[i].uuid;
        }
        return allResume;
    }

    int size() {
        return sizeStorage;
    }
}