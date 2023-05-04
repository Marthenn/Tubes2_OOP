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

    /**
     * Add a new Item to the StorerData
     * @param item Item to add
     * @param overwrite Whether to overwrite existing value or not if they already exist
     * @throws ItemWithIDAlreadyExist Item with the same ID already exists in the StorerData. Will not be thrown if overwrite is true.
     */
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

    /**
     * Add a new Item to the StorerData. Will not overwrite existing data.
     * @param item Item to add
     * @throws ItemWithIDAlreadyExist Item with the same ID already exists in the StorerData.
     * */
    public void addItem(T item) throws ItemWithIDAlreadyExist {
        addItem(item, false);
    }

    /**
     * Remove an item from the StorerData
     * @param id ID of the item to be removed
     * @param ignoreNotExist Don't throw an exception if the item doesn't exist in the StorerData
     * @return The removed item. If ignoreNotExist is true and the item does not exist, null will be returned.
     * @throws RemovedItemNotExist The item with the ID does not exist. Will not be thrown when ignoreNotExist is true.
     */
    public T removeItem(int id, boolean ignoreNotExist) throws RemovedItemNotExist {
        if (!store.containsKey(id) && !ignoreNotExist){
            String errorMessage = "Tidak ada " + storedItemName + " dengan ID yang diberikan!";
            throw new RemovedItemNotExist(errorMessage);
        }
        T removedItem = store.get(id);
        store.remove(id);
        return removedItem;
    }

    /**
     * Remove an item by the ID of the given item. Note that even if the item has different attributes, as long as the ID is the same, it will be recognized as the same item.
     * @param item
     * @param ignoreNotExist Don't throw if the item does not exist, return null instead.
     * @return The removed item. Null if ignoreNotExist is true and the item does not exist.
     * @throws RemovedItemNotExist The item with the ID does not exist.
     */
    public T removeItem(T item, boolean ignoreNotExist) throws RemovedItemNotExist {
        int removedID = item.getID();
        return removeItem(removedID, ignoreNotExist);
    }

    /**
     * Remove an item by the ID of the given item. Note that even if the item has different attributes, as long as the ID is the same, it will be recognized as the same item.
     * @param item
     * @return The removed item.
     * @throws RemovedItemNotExist The item with the ID does not exist.
     */
    public T removeItem(T item) throws RemovedItemNotExist {
        return removeItem(item, false);
    }

    /**
     * Remove an item by the given ID
     * @param id
     * @return The removed item
     * @throws RemovedItemNotExist The item with the ID does not exist.
     */
    public T removeItem(int id) throws RemovedItemNotExist {
        return removeItem(id, false);
    }


    /**
     *
     * @return The highest ID of all item in the StorerData
     */
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

    public int getNewID() {
        return  getHighestID() + 1;
    }

    public ArrayList<Integer> getIDList() {
        return new ArrayList<Integer>(store.keySet());
    }

    /**
     * Get item with the given id from the StorerData
     * @param id
     * @return Item with the matching ID
     * @throws SearchedItemNotExist There are no item with the given ID in the StorerData
     */
    public T getItem(int id) throws SearchedItemNotExist {
        if (!store.containsKey(id)) {
            String errorMessage = "Tidak ada " + storedItemName + " dengan ID yang diberikan!";
            throw new SearchedItemNotExist(errorMessage);
        }

        return store.get(id);
    }

    /**
     * Get an item by the ID of the given item. Note that even if the item has different attributes, as long as the ID is the same, it will be recognized as the same item.
     * @param item
     * @return Item with the matching ID
     * @throws SearchedItemNotExist There are no item with the given ID in the StorerData
     */
    public T getItem(T item) throws SearchedItemNotExist {
        return getItem(item.getID());
    }

    public ArrayList<T> getItemList() {
        return new ArrayList<T>(store.values());
    }



}
