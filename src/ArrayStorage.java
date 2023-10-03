/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for(int i=0; i< size(); i++)
            storage[i] = null;
    }

    void save(Resume r) {
        if (size()< storage.length)
            storage[size()]=r;
        else
            System.out.println("Хранилище заполнено полностью. Сохранить элемент не возможно.");
    }

    Resume get(String uuid) {
        for(Resume r : getAll()) {
            if (r.uuid.equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;
        for(int i=0; i<size(); i++){
            if (storage[i].uuid.equals(uuid)){
                index = i;
                break;
            }
        }
        int sizeStorage = size()-1;
        if (index > -1) {
            for (int i=index; i<sizeStorage; i++)
                storage[i] = storage[i+1];
        }
        storage[sizeStorage] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size()];
        if (size() >= 0) {
            System.arraycopy(storage, 0, result, 0, size());
        }
        return result;
    }

    int size() {
        int result = 0;
        for (Resume resume : storage)
            if (resume != null) {
                result++;
            } else {
                break;
            }
        return result;
    }
}