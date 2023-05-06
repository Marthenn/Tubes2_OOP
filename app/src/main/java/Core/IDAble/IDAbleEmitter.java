package Core.IDAble;

import java.util.ArrayList;

public interface IDAbleEmitter<T extends IDAbleListener<?>> extends IDAble {

    /**
     * Notify the listener of the changes to the IDAble object
     */
    void notifyListener();

    /**
     * Add new listener
     */
//    void addListener(T listener);

    void setListenerList(ArrayList<T> listeners);
}
