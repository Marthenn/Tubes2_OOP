package Core.DataStore.StorerData.Exception;

import Core.DataStore.StorerData.StorerData;
import Core.IDAble.IDAble;
import Core.IDAble.IDAbleEmitter;
import Core.IDAble.IDAbleListener;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Set;

public class StorerDataOfEmitter<T extends IDAbleEmitter<K>, K extends IDAbleListener<?>> extends StorerData<T> {

    public StorerDataOfEmitter(@NonNull String storedItemName) {
        super(storedItemName);
    }

    public void updateStoredItemListenerList(ArrayList<K> listener) {
        Set<Integer> keysOfStoredItem = this.getStore().keySet();

        for (Integer key : keysOfStoredItem) {
            T item = this.getStore().get(key);
            if (item == null) {
                continue;
            }
            item.setListenerList(listener);
        }

    }
}
