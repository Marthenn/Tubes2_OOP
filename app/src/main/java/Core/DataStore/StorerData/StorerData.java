package Core.DataStore.StorerData;


import Core.DataStore.StorerData.Exception.ItemWithIDAlreadyExist;
import Core.DataStore.StorerData.Exception.RemovedItemNotExist;
import Core.DataStore.StorerData.Exception.SearchedItemNotExist;
import Core.IDAble;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

public class StorerData<T extends IDAble> {
    private HashMap<Integer, T> store = new HashMap<Integer, T>();

    @Getter
    @NonNull
    private String storedItemName;
    public StorerData(String storedItemName) {
        this.storedItemName = storedItemName;
    }

    public void addItem(T item, boolean overwrite) throws ItemWithIDAlreadyExist {
        int addedID = item.getID();
        if (store.containsKey(addedID) && !overwrite){
            String errorMessage = storedItemName +
                    " dengan ID yang sama sudah ada. Tidak dapat menambahkan " +
                    storedItemName +
                    " item yang dibuat";
            throw new ItemWithIDAlreadyExist(errorMessage);
        }
        store.put(addedID, item);
    }

    public void addItem(T item) throws ItemWithIDAlreadyExist {
        addItem(item, false);
    }

    public T removeItem(int id, boolean ignoreNotExist) throws RemovedItemNotExist {
        if (!store.containsKey(id) && !ignoreNotExist){
            String errorMessage = "Tidak ada " + storedItemName + " dengan ID yang diberikan!";
            throw new RemovedItemNotExist(errorMessage);
        }
        T removedItem = store.get(id);
        store.remove(id);
        return removedItem;
    }

    public T removeItem(T item, boolean ignoreNotExist) throws RemovedItemNotExist {
        int removedID = item.getID();
        return removeItem(removedID, ignoreNotExist);
    }

    public T removeItem(T item) throws RemovedItemNotExist {
        return removeItem(item, false);
    }

    public T removeItem(int id) throws RemovedItemNotExist {
        return removeItem(id, false);
    }


    public int getHighestID() {
        Integer highestID = null;
        for (Integer id : store.keySet()) {
            if (highestID == null) {
                highestID = id;
                continue;
            }
            if (id > highestID) {
                highestID = id;
            }
        }

        if (highestID  == null) {
            return 0;
        }

        return highestID;
    }

    public ArrayList<Integer> getIDList() {
        return new ArrayList<Integer>(store.keySet());
    }

    public T getItem(int id) throws SearchedItemNotExist {
        if (!store.containsKey(id)) {
            String errorMessage = "Tidak ada " + storedItemName + " dengan ID yang diberikan!";
            throw new SearchedItemNotExist(errorMessage);
        }

        return store.get(id);
    }

    public T getItem(T item) throws SearchedItemNotExist {
        return getItem(item.getID());
    }



}
