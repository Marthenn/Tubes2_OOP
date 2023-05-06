package Core.DataStore.StorerData;

import Core.IDAble.IDAble;
import Core.IDAble.IDAbleListener;

import java.util.ArrayList;

public interface StorerDataEmitter<T extends StorerDataListener> {
    /**
     * Notify the owner
     */
    void notifySubscriber();

    void setListenerList(ArrayList<StorerDataListener> listeners);
}
