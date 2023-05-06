package Core.DataStore;

import Core.DataStore.StorerData.StorerDataListener;
import Core.IDAble.IDAble;
import Core.IDAble.IDAbleListener;

public interface StorerDataAndIDAbleListener<T extends IDAble> extends StorerDataListener, IDAbleListener<T> {
}
